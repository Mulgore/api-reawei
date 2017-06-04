package cn.reawei.api.controller;

import cn.reawei.api.common.utils.RSACoder;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Controller的作用是: 接口权限
 * <p>
 * Created by xingwu on 2017/5/26.
 */
@RestController
@RequestMapping("/v1/app/member")
public class AppMemberController extends BaseController {

    @Resource
    private IRwAppMemberService rwAppMemberService;

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 申请接口权限
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveAppMember() {
        this.response.setHeader("Access-Control-Allow-Origin", "*");
        RwAppMember appMember = new RwAppMember();
        appMember.setMemberId(864840390186938368l);
        boolean ret = false;
        try {
            Map<String, Object> keyMap = RSACoder.initKey();
            appMember.setPublicKey(RSACoder.getPublicKey(keyMap));
            appMember.setPrivateKey(RSACoder.getPrivateKey(keyMap));
            System.out.println(RSACoder.getPublicKey(keyMap));
            System.out.println(RSACoder.getPrivateKey(keyMap));

        } catch (Exception e) {
            e.printStackTrace();
            if (StringUtil.isNotBlank(e.getMessage())) {
                return toJSON("生成秘钥失败！");
            }
        }
        int rlt = rwAppMemberService.saveAppMember(appMember);
        if (rlt > 0) {
            ret = true;
        }
        return toJSON(ret);
    }
}
