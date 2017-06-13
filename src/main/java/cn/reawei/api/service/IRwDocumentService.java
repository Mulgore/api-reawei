package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwDocument;
import org.springframework.stereotype.Service;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个接口的作用是: 对文档信息禁用CRUD操作
 * <p>
 * Created by xingwu on 2017/6/8.
 */
@Service
public interface IRwDocumentService {

    /**
     * 查询文档分页集合
     *
     * @param documentQuery
     * @return
     */
    Result<RwDocument> getDocumentResultByQuery(Query<RwDocument> documentQuery);

    /**
     * 查询文档
     *
     * @param id 文档Id
     * @return
     */
    RwDocument getOneDocumentById(Long id);

    int updateDocumentById(RwDocument document);

    int saveDocument(RwDocument document);

    int removeDocument(Long docId);
}
