package cn.reawei.api.mapper;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwDocument;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Mapper的作用是: 访问数据库查询文章信息
 */
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