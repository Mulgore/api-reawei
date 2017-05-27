package cn.reawei.api.mapper;

import cn.reawei.api.model.RwApiInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RwApiInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwApiInfo record);

    int insertSelective(RwApiInfo record);

    RwApiInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwApiInfo record);

    int updateByPrimaryKey(RwApiInfo record);
}