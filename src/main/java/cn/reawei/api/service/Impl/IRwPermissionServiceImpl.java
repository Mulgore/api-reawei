package cn.reawei.api.service.Impl;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.mapper.RwPermissionMapper;
import cn.reawei.api.model.RwPermission;
import cn.reawei.api.service.IRwPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("rwPermissionService")
public class IRwPermissionServiceImpl implements IRwPermissionService {

    @Resource
    public RwPermissionMapper rwPermissionMapper;

    @Override
    public Result<RwPermission> getResultByQuery(Query<RwPermission> roleQuery) {
        Result<RwPermission> result = new Result<>();
        result.setTotal(rwPermissionMapper.countResultByQuery(roleQuery));
        result.setDataList(rwPermissionMapper.selectResultByQuery(roleQuery));
        return result;
    }

    @Override
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
    public boolean addRwPermission(RwPermission permission) {
        int ret = rwPermissionMapper.insertSelective(permission);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRwPermissionById(RwPermission permission) {
        int ret = rwPermissionMapper.updateByPrimaryKeySelective(permission);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    public RwPermission getRwPermissionById(Integer id) {
        return  rwPermissionMapper.selectByPrimaryKey(id * 1l);
    }
}
