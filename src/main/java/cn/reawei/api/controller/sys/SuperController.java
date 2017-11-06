package cn.reawei.api.controller.sys;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Controller的作用是: 这是Controller的老子
 * <p>
 * Created by xingwu on 2017/5/24.
 */
public class SuperController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    protected <T> Query<T> getQuery() {
        int page = 1, pageSize = 10;
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            page = Integer.parseInt(this.request.getParameter("page"));
        }
        if (StringUtils.isNotBlank(request.getParameter("pageSize"))) {
            pageSize = Integer.parseInt(this.request.getParameter("pageSize"));
        }
        return new Query<T>(null, (page - 1) * pageSize, pageSize, null);
    }

    /**
     * 返回登录 Token
     */
    protected SSOToken getSSOToken() {
        SSOToken tk = SSOHelper.getSSOToken(request);
        if (tk == null) {
            throw new RuntimeException("授权失败");
        }
        return tk;
    }

    /**
     * 用户ID
     */
    protected Integer getCurrentUserId() {
        try {
            return Integer.parseInt(getSSOToken().getId());
        } catch (RuntimeException e) {
            logger.warn("用户不存在");
            return null;
        }
    }

    /**
     * <p>
     * 转换为 bootstrap-table 需要的分页格式 JSON
     * </p>
     *
     * @param result 分页对象
     * @return
     */
    protected String jsonPageResult(Result<?> result) {
        Map<String, Object> data = new HashMap<>();
        data.put("data", result.getDataList());
        data.put("total", result.getTotal());
        data.put("success", true);
        return JSONObject.toJSONString(data);
    }

    protected String callbackSuccess(Map<String,Object> ret) {
        ret.put("success", true);
        return JSONObject.toJSONString(ret);
    }

    protected String callbackSuccess() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("success", true);
        return JSONObject.toJSONString(ret);
    }

    protected String callbackSuccess(String message) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("success", true);
        ret.put("message", message);
        return JSONObject.toJSONString(ret);
    }

    /**
     * 返回 JSON 格式对象
     * @param message
     * @return
     */
    protected String callbackFail(String message) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", false);
        data.put("message", message);
        return JSONObject.toJSONString(data);
    }

}
