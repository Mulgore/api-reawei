package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwPhotoInfo;
import cn.reawei.api.service.IRwPhotoInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.jetty.util.StringUtil;
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
    public String getPhotoResult(String appId, String name, String status) {
        Map<String, Object> ret = new HashMap<>();
        // 公钥验签
        if (checkAppIdAndDeskKeyPermission(appId, Constants.PHOTO_API_ID, ret)) {
            return toJSON(ret);
        }
        Query<RwPhotoInfo> photoInfoQuery = getQuery();
        RwPhotoInfo photoInfo = new RwPhotoInfo();
        if (StringUtil.isNotBlank(name)) {
            photoInfo.setTitle(name);
        }
        if (StringUtil.isNotBlank(status)){
            photoInfo.setStatus(Integer.parseInt(status));
        }
        photoInfoQuery.setQueryObject(photoInfo);
        OrderBy orderBy = new OrderBy();
        orderBy.setDesc(true);
        orderBy.setFieldName("id");
        photoInfoQuery.setOrderBy(orderBy);

        Result<RwPhotoInfo> result = rwPhotoInfoService.getPhotoInfoResultByQuery(photoInfoQuery);
        ret.put("data", result.getDataList());
        ret.put("total", result.getTotal());
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 存储照片信息接口
     *
     * @param appId appID和公钥
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/save/result/**", method = RequestMethod.POST)
    public String savePhotoInfo(String appId, RwPhotoInfo photoInfo) {
        Map<String, Object> ret = new HashMap<>();
        // 公钥验签
        if (checkAppIdAndDeskKeyPermission(appId, Constants.PHOTO_API_ID, ret)) {
            return toJSON(ret);
        }
        rwPhotoInfoService.savePhotoInfo(photoInfo);
        ret.put("data", true);
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 根据照片Id，修改照片接口
     *
     * @param appId appID和公钥
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/update/result/**", method = RequestMethod.PATCH)
    public String updatePhotoInfo(String appId, RwPhotoInfo photoInfo) {
        Map<String, Object> ret = new HashMap<>();
        // 公钥验签
        if (checkAppIdAndDeskKeyPermission(appId, Constants.PHOTO_API_ID, ret)) {
            return toJSON(ret);
        }
        rwPhotoInfoService.updatePhotoInfo(photoInfo);
        ret.put("data", true);
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 删除照片接口
     *
     * @param appId appID和公钥
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/remove/result/**", method = RequestMethod.DELETE)
    public String removePhotoInfo(String appId, Long id) {
        Map<String, Object> ret = new HashMap<>();
        // 公钥验签
        if (checkAppIdAndDeskKeyPermission(appId, Constants.PHOTO_API_ID, ret)) {
            return toJSON(ret);
        }
        rwPhotoInfoService.removePhotoInfoById(id);
        ret.put("data", true);
        return toJSON(ret);
    }
}
