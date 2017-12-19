package cn.reawei.api.service;

import cn.reawei.api.model.RwRole;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;

public interface IRwRoleService {

    Result<RwRole> getResultByQuery(Query<RwRole> roleQuery);

    boolean addRwRole(RwRole rwRole);

    boolean updateRwRoleById(RwRole rwRole);

    boolean getCheckByLevel(Integer level);

    boolean deleteById(Integer id);
}
