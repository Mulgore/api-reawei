package cn.reawei.api.mapper;

import cn.reawei.api.model.RwPermission;
import cn.reawei.common.page.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RwPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RwPermission record);

    int insertSelective(RwPermission record);

    RwPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwPermission record);

    int updateByPrimaryKey(RwPermission record);

    List<RwPermission> selectListByPid(Long id);

    List<RwPermission> selectResultByQuery(Query<RwPermission> permissionQuery);

    int countResultByQuery(Query<RwPermission> permissionQuery);
}