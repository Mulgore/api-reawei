package cn.reawei.api.mapper;

import cn.reawei.api.model.RwRole;
import cn.reawei.common.page.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RwRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwRole record);

    int insertSelective(RwRole record);

    int selectByLevel(Integer level);

    RwRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwRole record);

    int updateByPrimaryKey(RwRole record);

    List<RwRole> selectResultByQuery(Query<RwRole> roleQuery);

    int countResultByQuery(Query<RwRole> roleQuery);
}