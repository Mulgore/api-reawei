package cn.reawei.api.service.Impl;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.mapper.RwRoleMapper;
import cn.reawei.api.model.RwRole;
import cn.reawei.api.service.IRwRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("iRwRoleService")
public class IRwRoleServiceImpl implements IRwRoleService {

    @Resource
    RwRoleMapper rwRoleMapper;

    @Override
    public Result<RwRole> getResultByQuery(Query<RwRole> roleQuery) {
        Result<RwRole> result = new Result<>();
        result.setTotal(rwRoleMapper.countResultByQuery(roleQuery));
        result.setDataList(rwRoleMapper.selectResultByQuery(roleQuery));
        return result;
    }
}
