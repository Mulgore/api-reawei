package cn.reawei.api.mapper;

import cn.reawei.api.model.RwAddress;

public interface RwAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwAddress record);

    int insertSelective(RwAddress record);

    RwAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwAddress record);

    int updateByPrimaryKey(RwAddress record);
}