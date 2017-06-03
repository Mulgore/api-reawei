package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwDocument;
import org.springframework.stereotype.Service;

/**
 * Created by xingwu on 2017/5/26.
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
}
