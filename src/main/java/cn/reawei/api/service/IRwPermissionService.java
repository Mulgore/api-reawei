package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwPermission;

public interface IRwPermissionService {

    Result<RwPermission> getResultByQuery(Query<RwPermission> roleQuery);

    boolean deleteById(Integer id);

    boolean addRwPermission(RwPermission permission);

    boolean updateRwPermissionById(RwPermission permission);

    RwPermission getRwPermissionById(Integer id);
}
