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

    @Override
    public boolean addRwRole(RwRole rwRole) {
        int ret = rwRoleMapper.insertSelective(rwRole);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRwRoleById(RwRole rwRole) {
        int ret = rwRoleMapper.updateByPrimaryKeySelective(rwRole);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean getCheckByLevel(Integer level) {
        int ret = rwRoleMapper.selectByLevel(level);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        int ret = rwRoleMapper.deleteByPrimaryKey(id * 1l);
        if (ret > 0) {
            return true;
        }
        return false;
    }
}
