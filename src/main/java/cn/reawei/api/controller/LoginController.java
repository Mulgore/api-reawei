package cn.reawei.api.controller;

import cn.reawei.api.common.utils.MD5Util;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwUser;
import cn.reawei.api.service.IRwUserService;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.websocket.api.Session;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/api/v1/login")
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
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String login(String username, String password) {
        Map<String, Object> ret = new HashMap<>();
        if (StringUtil.isBlank(username) && StringUtil.isBlank(password)) {
            ret.put("success", false);
            ret.put("message", "用户名和密码为空");
            return toJSON(ret);
        }
        if (StringUtil.isBlank(username)) {
            ret.put("success", false);
            ret.put("message", "用户名为空");
            return toJSON(ret);
        }
        if (StringUtil.isBlank(password)) {
            ret.put("success", false);
            ret.put("message", "密码为空");
            return toJSON(ret);
        }
        System.out.println(MD5Util.encode(password).toLowerCase());
        RwUser user = rwUserService.getUserInfoByLoginName(username);
        if (user != null) {
            if (user.getPassword().toString().equals(MD5Util.encode(password).toLowerCase())) {
                ret.put("success", true);
                ret.put("message", "OK");
                Cookie cookie = new Cookie("token", user.getId().toString());
                cookie.setMaxAge(900000);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                this.response.addCookie(cookie);
            } else {
                ret.put("success", false);
                ret.put("message", "密码错误");
            }
        } else {
            ret.put("success", false);
            ret.put("message", "未注册");
        }
        return toJSON(ret);
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
    @RequestMapping(value = "/user/result", method = RequestMethod.GET)
    public String loginUser() {
        Map<String, Object> ret = new HashMap<>();
        Cookie[] cookies = this.request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName().toString())) {
                    ret.put("success", true);
                    Map<String, Object> data = new HashMap<>();
                    String[] str = new String[]{"dashboard", "users", "UIElement", "UIElementIconfont", "chart", "photo"};
                    data.put("permissions", str);
                    data.put("username", "xingwu");
                    ret.put("user", data);
                }
            }
        }

        return toJSON(ret);
    }

}
