package cn.reawei.api.mapper;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwDocument;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RwDocumentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwDocument record);

    int insertSelective(RwDocument record);

    RwDocument selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwDocument record);

    int updateByPrimaryKey(RwDocument record);

    List<RwDocument> selectResultByQuery(Query<RwDocument> documentQuery);

    int countResultByQuery(Query<RwDocument> documentQuery);
}