package cn.reawei.api.mapper;

import cn.reawei.api.model.RwAppMember;
import cn.reawei.common.page.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Mapper的作用是: 访问数据库查询用户接口权限信息
 */
@Repository
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