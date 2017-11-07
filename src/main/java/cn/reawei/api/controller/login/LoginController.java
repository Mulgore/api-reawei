package cn.reawei.api.controller.login;

import cn.reawei.api.common.utils.MD5Util;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwUser;
import cn.reawei.api.service.IRwUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.common.SSOConstants;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                String remember = request.getParameter("remember");
                if (!Objects.isNull(remember) && "true".equals(remember)) {
                    request.setAttribute(SSOConstants.SSO_COOKIE_MAXAGE, 302400);
                }
                SSOHelper.setCookie(request, response, ssoToken, true);
                Map<String, Object> data = new HashMap<>();
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
            if (Objects.isNull(rwUser)) {
                SSOHelper.clearLogin(request, response);
                return callbackFail("无效用户");
            }
            Map<String, Object> data = new HashMap<>();
            Map<String, Object> userData = new HashMap<>();
            Map<String, Object> permissions = new HashMap<>();
            permissions.put("role", "admin");
            userData.put("permissions", permissions);
            userData.put("username", "管理员");
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

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SSOHelper.clearLogin(request, response);
        request.getSession().invalidate();
    }


    @RequestMapping(value = "/user/sendSms", method = RequestMethod.POST)
    public String sendSms() {
        // 过滤 XSS SQL 注入
        WafRequestWrapper wr = new WafRequestWrapper(request);
        String mobile = wr.getParameter("mobile");
//        if (StringUtil.isBlank(mobile)) {
//            return callbackFail("手机号不能为空 !");
//        }
//        UserVo userVo = userService.findByMobile(mobile);
//        if (userVo == null) {
//            return callbackFail("账户不存在 !");
//        }
//        // 先固定使用些appId发送短信
//        final Integer appId = Sms.FULA_APPID;
//        final Integer templeId = Sms.FULA_TPLID_VERIFY;
//        Map<String, Object> data = new HashMap<>();
//        try {
//            Random random = new Random();
//            String authCode = "";
//            for (int i = 0; i < 4; i++) {
//                authCode = authCode + random.nextInt(9);
//            }
//            String ip = RequestUtils.getIpAddr(request);
//            FmSmsTemplate smsTemplate = messageService.getSmsTmplById(templeId);
//            String content = smsTemplate.getContent().replace("#vcode#", authCode);
//            smsApiService.sendSmsByHttp(mobile, content, appId, templeId, ip);
//            Object token = TokenUtil.token(TokenUtil.formatJsonToken(mobile, authCode), true, null);
//            redisUtil.set("token_" + mobile, token, (long) (60 * 30));
//            data.put("token", token);
//            data.put("message", "验证码发送成功 !");
//            return callbackSuccess(data);
//        } catch (Exception e) {
//            logger.error("send sms exception: {}", e);
//            return callbackFail("短信验证码发送失败!");
//        }
        return callbackSuccess();
    }

    @RequestMapping(value = "/user/forgot", method = RequestMethod.POST)
    public String forgot() {
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        if (Objects.isNull(mobile) && Objects.isNull(password)) {
            return callbackFail("账号和密码不能为空");
        }
        if (Objects.isNull(mobile)) {
            return callbackFail("账号不能为空");
        }
        if (Objects.isNull(password)) {
            return callbackFail("密码不能为空");
        }
        RwUser rwUser = rwUserService.getUserInfoByLoginName(mobile);
        if (Objects.isNull(rwUser)) {
            return callbackFail("账号未注册");
        }
        rwUser.setPassword(MD5Util.encode(password).toLowerCase());
        rwUserService.updateUserById(rwUser);
        return callbackSuccess("密码修改成功");
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String update() {
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String checkPassword = request.getParameter("checkPassword");
        if (!checkPassword.equals(password)) {
            return callbackFail("确认密码与新密码不一致 !");
        }
        RwUser user = rwUserService.getUserInfoById(getCurrentUserId());
        if (MD5Util.encode(oldPassword).toLowerCase().equals(user.getPassword())) {
            Map<String, Object> data = new HashMap<>();
            user.setPassword(MD5Util.encode(password).toLowerCase());
            rwUserService.updateUserById(user);
            data.put("message", "密码修改成功 !");
            return callbackSuccess(data);
        } else {
            return callbackFail("原密码不正确, 修改失败");
        }
    }
}
