package cn.reawei.api.service;

import cn.reawei.api.model.RwPermission;
import cn.reawei.api.model.RwRolePermission;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;

public interface IRwRolePermissionService {

    Result<RwRolePermission> getResultByQuery(Query<RwRolePermission> roleQuery);

    boolean deleteByPidAndLevel(Integer pid,Integer level);

    boolean getByPidAndLevel(Integer pid,Integer level);

    boolean addRwRolePermission(RwRolePermission permission);

    boolean updateRwRolePermissionById(RwRolePermission permission);

    RwPermission getRwRolePermissionById(Integer id);
}
