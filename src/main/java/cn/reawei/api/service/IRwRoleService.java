package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwRole;

public interface IRwRoleService {

    Result<RwRole> getResultByQuery(Query<RwRole> roleQuery);
}
