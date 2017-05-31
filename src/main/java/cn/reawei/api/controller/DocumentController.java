package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.model.RwDocument;
import cn.reawei.api.model.RwPhotoInfo;
import cn.reawei.api.service.IRwAppMemberService;
import cn.reawei.api.service.IRwDocumentService;
import cn.reawei.api.service.IRwPhotoInfoService;
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
@RequestMapping("/v1/doc")
public class DocumentController extends BaseController {

    @Resource
    private IRwAppMemberService rwAppMemberService;
    @Resource
    private IRwDocumentService rwDocumentService;

    @ResponseBody
    @RequestMapping(value = "/result/**", method = RequestMethod.GET)
    public String equals(String appId) {

        this.response.setHeader("Access-Control-Allow-Origin", "*");
        String path = this.request.getServletPath();
        String deskKey = path.substring(path.indexOf("result/") + 7, path.lastIndexOf("."));
        Map<String, Object> ret = new HashMap<>();
        String checkStr = checkAppIdAndDeskKey(appId, deskKey);
        if (StringUtil.isNotBlank(checkStr) && !"{}".equals(checkStr)) {
            return checkStr;
        }
        RwAppMember appMember = rwAppMemberService.getAppMemberById(Long.parseLong(appId));
        switch (appMember.getLevel()) {
            case 0:
                if (appMember.getNumberTotal() >= 500) {
                    ret.put("code", Constants.PHOTO_CODE_ERROR_TOTAL_NUMBER_MAX);
                    ret.put("msg", "接口调用上限！");
                    return toJSON(ret);
                }
                break;
            case 1:
                if (appMember.getNumberTotal() >= 2000) {
                    ret.put("code", Constants.PHOTO_CODE_ERROR_TOTAL_NUMBER_MAX);
                    ret.put("msg", "接口调用上限！");
                    return toJSON(ret);
                }
                break;
            case 2:
                if (appMember.getNumberTotal() >= 5000) {
                    ret.put("code", Constants.PHOTO_CODE_ERROR_TOTAL_NUMBER_MAX);
                    ret.put("msg", "接口调用上限！");
                    return toJSON(ret);
                }
                break;
            case 3:
                if (appMember.getNumberTotal() >= 10000) {
                    ret.put("code", Constants.PHOTO_CODE_ERROR_TOTAL_NUMBER_MAX);
                    ret.put("msg", "接口调用上限！");
                    return toJSON(ret);
                }
        }
        appMember.setNumberTotal(appMember.getNumberTotal() + 1);
        rwAppMemberService.updateAppMemberById(appMember);
        Query<RwDocument> documentQuery = new Query<>();
        RwDocument photoInfo = new RwDocument();
        documentQuery.setQueryObject(photoInfo);
        OrderBy orderBy = new OrderBy();
        orderBy.setDesc(true);
        orderBy.setFieldName("id");
        documentQuery.setOrderBy(orderBy);
        ret.put("code", 0);

        Result<RwDocument> result = rwDocumentService.getDocumentResultByQuery(documentQuery);
        ret.put("data", result.getDataList());
        return toJSON(ret);
    }
}
