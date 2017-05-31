package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.model.RwPhotoInfo;
import cn.reawei.api.service.IRwAppMemberService;
import cn.reawei.api.service.IRwPhotoInfoService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingwu on 2017/5/26.
 */
@RestController
@RequestMapping("/v1/photo")
public class PhotoController extends BaseController {

    @Resource
    private IRwAppMemberService rwAppMemberService;
    @Resource
    private IRwPhotoInfoService rwPhotoInfoService;

    @ResponseBody
    @RequestMapping(value = "/result/**", method = RequestMethod.GET)
    public String equals(String appId) {

        this.response.setHeader("Access-Control-Allow-Origin", "*");
        String path = this.request.getServletPath();
        String deskKey = path.substring(path.indexOf("result/") + 7, path.lastIndexOf("."));
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKey(appId, deskKey, ret)) {
            return toJSON(ret);
        }
        if (updateLevelUseNumber(appId, ret)) {
            return toJSON(ret);
        }
        RwAppMember appMember = rwAppMemberService.getAppMemberById(Long.parseLong(appId));
        if (!"100078".equals(appMember.getApiId().toString())) {
            ret.put("code", Constants.CODE_ERROR_APP_ID_NOT_PERM);
            ret.put("msg", "AppId没有权限!!!");
            return toJSON(ret);
        }
        Query<RwPhotoInfo> photoInfoQuery = new Query<>();
        RwPhotoInfo photoInfo = new RwPhotoInfo();
        photoInfo.setStatus(0);
        photoInfoQuery.setQueryObject(photoInfo);
        System.out.println(photoInfoQuery.getQueryObject().getStatus());
        OrderBy orderBy = new OrderBy();
        orderBy.setDesc(true);
        orderBy.setFieldName("id");
        photoInfoQuery.setOrderBy(orderBy);
        ret.put("code", 0);

        Result<RwPhotoInfo> result = rwPhotoInfoService.getPhotoInfoResultByQuery(photoInfoQuery);
        ret.put("data", result.getDataList());
        return toJSON(ret);
    }
}
