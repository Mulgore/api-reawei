package cn.reawei.api.mapper;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.model.RwMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RwMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwMember record);

    int insertSelective(RwMember record);

    RwMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwMember record);

    int updateByPrimaryKey(RwMember record);


    List<RwMember> selectResultByQuery(Query<RwMember> memberQuery);

    int countResultByQuery(Query<RwMember> memberQuery);
}