package cn.reawei.api.service.Impl;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.mapper.RwDocumentMapper;
import cn.reawei.api.model.RwDocument;
import cn.reawei.api.service.IRwDocumentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xingwu on 2017/5/31.
 */
@Service("rwDocumentService")
public class IRwDocumentServiceImpl implements IRwDocumentService {

    @Resource
    private RwDocumentMapper rwDocumentMapper;

    @Override
    public Result<RwDocument> getDocumentResultByQuery(Query<RwDocument> documentQuery) {
       Result<RwDocument> result = new Result<>();
        result.setDataList(rwDocumentMapper.selectResultByQuery(documentQuery));
        result.setTotal(rwDocumentMapper.countResultByQuery(documentQuery));
        return result;
    }
}
