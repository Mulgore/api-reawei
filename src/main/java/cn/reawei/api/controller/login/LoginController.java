package cn.reawei.api.controller.login;

import cn.reawei.api.common.utils.MD5Util;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwUser;
import cn.reawei.api.service.IRwUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Controller的作用是: 登陆接口
 * <p>
 * Created by xingwu on 2017/5/26.
 */
@RestController
@RequestMapping("/api/v1")
public class LoginController extends BaseController {

    @Resource
    private IRwUserService rwUserService;

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 登陆验证接口
     *
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login() {
        WafRequestWrapper wr = new WafRequestWrapper(this.request);
        String username = wr.getParameter("username");
        String password = wr.getParameter("password");
        if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
            return callbackFail("用户名和密码为空");
        }
        if (StringUtils.isBlank(username)) {
            return callbackFail("用户名为空");
        }
        if (StringUtils.isBlank(password)) {
            return JSONObject.toJSONString("密码为空");
        }
        username = username.replaceAll(" ", "");
        password = password.replaceAll(" ", "");
        RwUser user = rwUserService.getUserInfoByLoginName(username);
        if (user != null) {
            if (user.getPassword().toString().equals(MD5Util.encode(password).toLowerCase())) {
                SSOToken ssoToken = new SSOToken();
                ssoToken.setId(user.getId());
                ssoToken.setUserAgent(user.getLoginName());
                SSOHelper.setCookie(request, response, ssoToken, true);
                Map<String,Object> data = new HashMap<>();
                return callbackSuccess(data);
            } else {
                return callbackFail("密码错误");
            }
        } else {
            return callbackFail("未注册");
        }
    }


    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 登陆验证Cookie里面的token 然后返回用户的用户名，以及权限菜单
     *
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String checkLogin() {
        SSOToken token = SSOHelper.getSSOToken(request);
        if (!Objects.isNull(token)) {
            if (Objects.isNull(token.getId())) {
                SSOHelper.clearLogin(request, response);
                return callbackFail("无效用户");
            }
            RwUser rwUser = rwUserService.getUserInfoById(Integer.parseInt(token.getId()));
            if(Objects.isNull(rwUser)){
                SSOHelper.clearLogin(request, response);
                return callbackFail("无效用户");
            }
            Map<String, Object> data = new HashMap<>();
            Map<String, Object> userData = new HashMap<>();
            Map<String, Object> permissions = new HashMap<>();
            permissions.put("role", "admin");
            userData.put("permissions", permissions);
            userData.put("username", token.getUserAgent());
            data.put("user", userData);
            return callbackSuccess(data);
        }
        return callbackFail("没有登录");
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 权限菜单
     *
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public String menusList() {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> index = new HashMap<>();
        index.put("id", "1");
        index.put("icon", "home");
        index.put("name", "管理中心");
        index.put("route", "/dashboard");
        data.add(index);
        Map<String, Object> index2 = new HashMap<>();
        index2.put("id", "2");
        index2.put("bpid", "1");
        index2.put("name", "基础管理");
        index2.put("icon", "setting");
        data.add(index2);
        Map<String, Object> index21 = new HashMap<>();
        index21.put("id", "21");
        index21.put("bpid", "2");
        index21.put("mpid", "2");
        index21.put("icon", "user");
        index21.put("name", "角色管理");
        index21.put("route", "/settingRole");
        data.add(index21);
        Map<String, Object> index23 = new HashMap<>();
        index23.put("id", "22");
        index23.put("bpid", "2");
        index23.put("mpid", "2");
        index23.put("icon", "contacts");
        index23.put("name", "权限管理");
        index23.put("route", "/settingPermission");
        data.add(index23);
        return JSONObject.toJSONString(data);
    }
}
