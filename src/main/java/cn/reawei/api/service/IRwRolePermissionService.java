package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwPermission;
import cn.reawei.api.model.RwRolePermission;

public interface IRwRolePermissionService {

    Result<RwRolePermission> getResultByQuery(Query<RwRolePermission> roleQuery);

    boolean deleteByPidAndLevel(Integer pid,Integer level);

    boolean getByPidAndLevel(Integer pid,Integer level);

    boolean addRwRolePermission(RwRolePermission permission);

    boolean updateRwRolePermissionById(RwRolePermission permission);

    RwPermission getRwRolePermissionById(Integer id);
}
