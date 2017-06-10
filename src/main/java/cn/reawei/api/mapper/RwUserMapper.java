package cn.reawei.api.mapper;

import cn.reawei.api.model.RwUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RwUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwUser record);

    int insertSelective(RwUser record);

    RwUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwUser record);

    int updateByPrimaryKey(RwUser record);

    RwUser selectByLoginName(String loginName);
}