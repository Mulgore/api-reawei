package cn.reawei.api.mapper;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RwRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwRole record);

    int insertSelective(RwRole record);

    RwRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwRole record);

    int updateByPrimaryKey(RwRole record);

    List<RwRole> selectResultByQuery(Query<RwRole> roleQuery);

    int countResultByQuery(Query<RwRole> roleQuery);
}