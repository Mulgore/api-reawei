package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwPermissionMapper;
import cn.reawei.api.model.RwPermission;
import cn.reawei.api.service.IRwPermissionService;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("rwPermissionService")
public class IRwPermissionServiceImpl implements IRwPermissionService {

    @Resource
    public RwPermissionMapper rwPermissionMapper;

    @Override
    @Transactional
    public Result<RwPermission> getResultByQuery(Query<RwPermission> roleQuery) {
        Result<RwPermission> result = new Result<>();
        result.setTotal(rwPermissionMapper.countResultByQuery(roleQuery));
        result.setDataList(rwPermissionMapper.selectResultByQuery(roleQuery));
        return result;
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        int ret = rwPermissionMapper.deleteByPrimaryKey(id * 1l);

        if (ret > 0) {
            List<RwPermission> list = rwPermissionMapper.selectListByPid(id * 1l);
            for (RwPermission perm : list) {
                rwPermissionMapper.deleteByPrimaryKey(perm.getId());
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean addRwPermission(RwPermission permission) {
        int ret = rwPermissionMapper.insertSelective(permission);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateRwPermissionById(RwPermission permission) {
        int ret = rwPermissionMapper.updateByPrimaryKeySelective(permission);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public RwPermission getRwPermissionById(Integer id) {
        return  rwPermissionMapper.selectByPrimaryKey(id * 1l);
    }
}
