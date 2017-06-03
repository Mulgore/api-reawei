package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.model.RwPhotoInfo;
import cn.reawei.api.service.IRwAppMemberService;
import cn.reawei.api.service.IRwPhotoInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Controller的作用是: 照片墙接口
 * <p>
 * Created by xingwu on 2017/5/26.
 */
@RestController
@RequestMapping("/v1/photo")
public class PhotoController extends BaseController {

    @Resource
    private IRwPhotoInfoService rwPhotoInfoService;

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 请求照片墙接口列表
     *
     * @param appId appID和公钥
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/result/**", method = RequestMethod.GET)
    public String getPhotoResult(String appId) {

        Map<String, Object> ret = new HashMap<>();
        // 公钥验签
        if (checkAppIdAndDeskKeyPermission(appId, Constants.PHOTO_API_ID, ret)) {
            return toJSON(ret);
        }
        Query<RwPhotoInfo> photoInfoQuery = new Query<>();
        RwPhotoInfo photoInfo = new RwPhotoInfo();
        photoInfo.setStatus(0);
        photoInfoQuery.setQueryObject(photoInfo);
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
