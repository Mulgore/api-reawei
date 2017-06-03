package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.controller.sys.BaseController;
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
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Controller的作用是: 文档接口
 * <p>
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
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 请求文章列表
     *
     * @param appId appId和公钥
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/result/**", method = RequestMethod.GET)
    public String getDocResult(String appId) {
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKey(appId, ret)) {
            return toJSON(ret);
        }
        if (updateLevelUseNumber(appId, ret)) {
            return toJSON(ret);
        }
        //检查接口权限
        if (checkAppIdPermission(appId, ret, Constants.DOCUMENT_API_ID)) return toJSON(ret);
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

    @RequestMapping(value = "/info/result/**", method = RequestMethod.GET)
    public String getOneDocument(String appId, Long Id) {
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKey(appId, ret)) {
            return toJSON(ret);
        }
        if (updateLevelUseNumber(appId, ret)) {
            return toJSON(ret);
        }
        //检查接口权限
        if (checkAppIdPermission(appId, ret, Constants.DOCUMENT_API_ID)) return toJSON(ret);

        ret.put("code", 0);
        RwDocument result = rwDocumentService.getOneDocumentById(Id);
        ret.put("data", result);
        return toJSON(ret);
    }

}
