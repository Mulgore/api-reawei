package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwDocument;
import cn.reawei.api.service.IRwDocumentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private IRwDocumentService rwDocumentService;

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 请求文章列表接口
     *
     * @param appId appId和公钥
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/result/**", method = RequestMethod.GET)
    public String getDocResult(String appId) {
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKeyPermission(appId, Constants.DOCUMENT_API_ID, ret)) {
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

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 根据文章Id查询文档内容详情接口
     *
     * @param appId appId和公钥
     * @param docId 文档Id
     * @return
     */
    @RequestMapping(value = "/info/result/**", method = RequestMethod.GET)
    public String getOneDocument(String appId, Long docId) {
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKeyPermission(appId, Constants.DOCUMENT_API_ID, ret)) {
            return toJSON(ret);
        }
        RwDocument result = rwDocumentService.getOneDocumentById(docId);
        ret.put("code", 0);
        ret.put("data", true);
        if (result == null) {
            ret.put("code", Constants.CODE_DOCUMENT_INFO_IS_NULL);
            ret.put("data", false);
            ret.put("message","文档不存在");
            return toJSON(ret);
        }
        ret.put("result",result);
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 根据文章Id修改文档信息接口
     *
     * @param appId appId和公钥
     * @return
     */
    @RequestMapping(value = "/update/result/**", method = RequestMethod.PATCH)
    public String updateDocument(String appId, RwDocument document) {
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKeyPermission(appId, Constants.DOCUMENT_API_ID, ret)) {
            return toJSON(ret);
        }
        int rlt = rwDocumentService.updateDocumentById(document);
        ret.put("code", Constants.CODE_DOCUMENT_UPDATE_IS_ERROR);
        ret.put("data", false);
        if (rlt == 0) {
            ret.put("code", 0);
            ret.put("data", true);
        }
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 添加文档信息接口
     *
     * @param appId appId和公钥
     * @return
     */
    @RequestMapping(value = "/save/result/**", method = RequestMethod.POST)
    public String saveDocument(String appId, RwDocument document) {
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKeyPermission(appId, Constants.DOCUMENT_API_ID, ret)) {
            return toJSON(ret);
        }
        int rlt = rwDocumentService.saveDocument(document);
        ret.put("code", Constants.CODE_DOCUMENT_SAVE_IS_ERROR);
        ret.put("data", false);
        if (rlt == 0) {
            ret.put("code", 0);
            ret.put("data", true);
        }
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 删除文档信息接口
     *
     * @param appId appId和公钥
     * @return
     */
    @RequestMapping(value = "/remove/result/**", method = RequestMethod.DELETE)
    public String removeDocument(String appId, Long docId) {
        Map<String, Object> ret = new HashMap<>();
        if (checkAppIdAndDeskKeyPermission(appId, Constants.DOCUMENT_API_ID, ret)) {
            return toJSON(ret);
        }
        int rlt = rwDocumentService.removeDocument(docId);
        ret.put("code", Constants.CODE_DOCUMENT_REMOVE_IS_ERROR);
        ret.put("data", false);
        if (rlt == 0) {
            ret.put("code", 0);
            ret.put("data", true);
        }
        return toJSON(ret);
    }
}
