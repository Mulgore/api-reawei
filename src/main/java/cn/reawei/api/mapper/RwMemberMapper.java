package cn.reawei.api.mapper;

import cn.reawei.api.model.RwMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RwMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwMember record);

    int insertSelective(RwMember record);

    RwMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwMember record);

    int updateByPrimaryKey(RwMember record);
}