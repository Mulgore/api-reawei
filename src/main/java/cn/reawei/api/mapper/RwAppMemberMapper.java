package cn.reawei.api.mapper;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwAddress;
import cn.reawei.api.model.RwAppMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RwAppMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwAppMember record);

    int insertSelective(RwAppMember record);

    RwAppMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwAppMember record);

    int updateByPrimaryKey(RwAppMember record);

    List<RwAppMember> selectResultByQuery(Query<RwAppMember> appMemberQuery);

    int countResultByQuery(Query<RwAppMember> appMemberQuery);
}