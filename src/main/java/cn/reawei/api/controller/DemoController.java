package cn.reawei.api.controller;

import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.service.IRwAddressService;
import cn.reawei.api.service.IRwCacheService;
import cn.reawei.api.service.IRwMemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Controller的作用是: 测试
 * <p>
 * Created by xingwu on 2017/5/24.
 */
@RestController
public class DemoController extends BaseController {

    @Resource
    private IRwAddressService rwAddressService;
    @Resource
    private IRwCacheService rwCacheService;
    @Resource
    private IRwMemberService rwMemberService;

    @RequestMapping("/test")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/user")
    public String getAddress() {
        return toJSON(rwAddressService.getRwAddressResultByQuery(null));
    }

    @RequestMapping("/cache")
    public String getCache() {
        return toJSON(rwCacheService.getByCache());
    }

    @RequestMapping("/member")
    public String getMemberById() {
        return toJSON(rwMemberService.getRwMemberById(864850852613988352L));
    }

    @RequestMapping("/exception")
    public String  testException(){
       try{
            Integer a = null;
           System.out.println(a.toString());
       }catch (Exception e){
           throw new RuntimeException(e);
       }
        return "ss";
    }

}
