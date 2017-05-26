package cn.reawei.api.controller;

import cn.reawei.api.common.utils.RSACoder;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingwu on 2017/5/26.
 */
@RestController
@RequestMapping("/v1/app/member")
public class AppMemberController extends BaseController {

    @Resource
    private IRwAppMemberService rwAppMemberService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveAppMember() {
        RwAppMember appMember = new RwAppMember();
        appMember.setMemberId(864840390186938368l);
        boolean ret = false;
        try {
            Map<String, Object> keyMap = RSACoder.initKey();
            appMember.setPublicKey(RSACoder.getPublicKey(keyMap));
            appMember.setPrivateKey(RSACoder.getPrivateKey(keyMap));
            System.out.println(RSACoder.getPublicKey(keyMap));
            System.out.println(RSACoder.getPrivateKey(keyMap));

        }catch (Exception e){
            e.printStackTrace();
            if (StringUtil.isNotBlank(e.getMessage())){
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
