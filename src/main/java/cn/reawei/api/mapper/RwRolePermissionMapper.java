package cn.reawei.api.mapper;

import cn.reawei.api.model.RwRolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RwRolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPermIdAndLevel(@Param("pid")Long pid, @Param("level")Long level);

    int insert(RwRolePermission record);

    int insertSelective(RwRolePermission record);

    RwRolePermission selectByPrimaryKey(Long id);

    RwRolePermission selectByPidAndLevel(@Param("pid")Long pid, @Param("level") Long level);

    int updateByPrimaryKeySelective(RwRolePermission record);

    int updateByPrimaryKey(RwRolePermission record);
}