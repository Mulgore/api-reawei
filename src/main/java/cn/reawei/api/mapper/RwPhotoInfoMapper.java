package cn.reawei.api.mapper;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwPhotoInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RwPhotoInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwPhotoInfo record);

    int insertSelective(RwPhotoInfo record);

    RwPhotoInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwPhotoInfo record);

    int updateByPrimaryKey(RwPhotoInfo record);

    List<RwPhotoInfo> selectResultByQuery(Query<RwPhotoInfo> photoInfoQuery);

    int countResultByQuery(Query<RwPhotoInfo> photoInfoQuery);
}