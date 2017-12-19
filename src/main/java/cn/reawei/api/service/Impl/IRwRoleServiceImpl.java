package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwRoleMapper;
import cn.reawei.api.model.RwRole;
import cn.reawei.api.service.IRwRoleService;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("iRwRoleService")
public class IRwRoleServiceImpl implements IRwRoleService {

    @Resource
    RwRoleMapper rwRoleMapper;

    @Override
    @Transactional
    public Result<RwRole> getResultByQuery(Query<RwRole> roleQuery) {
        Result<RwRole> result = new Result<>();
        result.setTotal(rwRoleMapper.countResultByQuery(roleQuery));
        result.setDataList(rwRoleMapper.selectResultByQuery(roleQuery));
        return result;
    }

    @Override
    @Transactional
    public boolean addRwRole(RwRole rwRole) {
        int ret = rwRoleMapper.insertSelective(rwRole);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateRwRoleById(RwRole rwRole) {
        int ret = rwRoleMapper.updateByPrimaryKeySelective(rwRole);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean getCheckByLevel(Integer level) {
        int ret = rwRoleMapper.selectByLevel(level);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        int ret = rwRoleMapper.deleteByPrimaryKey(id * 1l);
        if (ret > 0) {
            return true;
        }
        return false;
    }
}
