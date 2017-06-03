package cn.reawei.api.controller.sys;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.AjaxResult;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Resource
    private IRwAppMemberService rwAppMemberService;


    /**
     * 转化成JSON格式
     *
     * @param object 参数
     * @return 返回JSON字符串
     */
    protected String toJSON(Object object) {
        return JSONObject.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * 返回 JSON 格式对象
     *
     * @param object 转换对象
     * @param format 序列化特点
     * @return
     */
    protected String toJSON(Object object, String format) {
        if (format == null) {
            return toJSON(object);
        }
        return JSONObject.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * <p>
     * 自动判定是否有跨域操作,转成字符串并返回
     * </p>
     *
     * @param object
     * @return 跨域或不跨域的字符串
     */
    protected String callback(AjaxResult object) {
        return callback(object, null);
    }

    protected String callback(AjaxResult object, String format) {
        String callback = request.getParameter("callback");
        if (callback == null) {
            /**
             * 非 JSONP 请求
             */
            return toJSON(object, format);
        }
        StringBuffer json = new StringBuffer();
        json.append(callback);
        json.append("(").append(toJSON(object, format)).append(")");
        return json.toString();
    }

    protected String callbackSuccess(Object obj) {
        return callback(new AjaxResult(obj));
    }

    protected String callbackFail(String message) {
        return callback(new AjaxResult(false, message));
    }

    /**
     * 根据请求的URL,截取公钥
     *
     * @param path 请求URL
     * @return 公钥字符串
     */
    protected String getDeskKey(String path) {
        return path.substring(path.indexOf("result/") + 7, path.lastIndexOf("."));
    }

    protected boolean checkAppIdPermission(String appId, Map<String, Object> ret, String apiId) {
        RwAppMember appMember = rwAppMemberService.getAppMemberById(Long.parseLong(appId));
        if (!apiId.equals(appMember.getApiId().toString())) {
            ret.put("code", Constants.CODE_ERROR_APP_ID_NOT_PERM);
            ret.put("msg", "AppId没有权限!!!");
            return true;
        }
        return false;
    }
}
