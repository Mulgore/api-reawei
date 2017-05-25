package cn.reawei.api.controller;

import cn.reawei.api.service.IRwAddressService;
import cn.reawei.api.service.IRwCacheService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xingwu on 2017/5/24.
 */
@RestController
public class DemoController {

    @Resource
    private IRwAddressService rwAddressService;
    @Resource
    private IRwCacheService rwCacheService;

    @RequestMapping("/test")
    public String hello(){
        return "Hello World!";
    }

    @RequestMapping("/user")
    @ResponseBody
    public String getAddress(){
        return null;
    }

    @RequestMapping("/cache")
    @ResponseBody
    public String getCache(){
        return JSONObject.toJSONString(rwCacheService.getByCache());
    }
}
