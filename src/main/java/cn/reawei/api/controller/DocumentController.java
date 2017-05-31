package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.model.RwDocument;
import cn.reawei.api.service.IRwAppMemberService;
import cn.reawei.api.service.IRwDocumentService;
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

    /**
     * 请求文章列表
     *
     * @param appId appId和公钥
     * @return 返回JSON格式的字符串
     */
    @ResponseBody
    @RequestMapping(value = "/result/**", method = RequestMethod.GET)
    public String getDocResult(String appId) {

        this.response.setHeader("Access-Control-Allow-Origin", "*");
        String path = this.request.getServletPath();
        String deskKey = getDeskKey(path);
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKey(appId, deskKey, ret)) {
            return toJSON(ret);
        }
        if (updateLevelUseNumber(appId, ret)) {
            return toJSON(ret);
        }
        RwAppMember appMember = rwAppMemberService.getAppMemberById(Long.parseLong(appId));
        if (!"100079".equals(appMember.getApiId().toString())) {
            ret.put("code", Constants.CODE_ERROR_APP_ID_NOT_PERM);
            ret.put("msg", "AppId没有权限!!!");
            return toJSON(ret);
        }
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
