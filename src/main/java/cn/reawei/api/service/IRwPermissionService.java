package cn.reawei.api.service;

import cn.reawei.api.model.RwPermission;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;

public interface IRwPermissionService {

    Result<RwPermission> getResultByQuery(Query<RwPermission> roleQuery);

    boolean deleteById(Integer id);

    boolean addRwPermission(RwPermission permission);

    boolean updateRwPermissionById(RwPermission permission);

    RwPermission getRwPermissionById(Integer id);
}
