package cn.reawei.api.service.Impl;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.mapper.RwPermissionMapper;
import cn.reawei.api.mapper.RwRolePermissionMapper;
import cn.reawei.api.model.RwPermission;
import cn.reawei.api.model.RwRolePermission;
import cn.reawei.api.service.IRwRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service("rwRolePermissionService")
public class IRwRolePermissionServiceImpl implements IRwRolePermissionService {

    @Resource
    public RwRolePermissionMapper rwRolePermissionMapper;
    @Resource
    public RwPermissionMapper rwPermissionMapper;


    @Override
    public boolean deleteByPidAndLevel(Integer id, Integer level) {
        int ret = 0;
        RwPermission permission = rwPermissionMapper.selectByPrimaryKey(id * 1l);
        if (!Objects.isNull(permission) && Objects.equals(0, permission.getPid())) {
            List<RwPermission> list = rwPermissionMapper.selectListByPid(permission.getId());
            for (RwPermission perm : list) {
                rwRolePermissionMapper.deleteByPermIdAndLevel(perm.getId(), level * 1l);
            }
            ret = rwRolePermissionMapper.deleteByPermIdAndLevel(id * 1l, level * 1l);
        } else {
            ret = rwRolePermissionMapper.deleteByPermIdAndLevel(id * 1l, level * 1l);
        }
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Result<RwRolePermission> getResultByQuery(Query<RwRolePermission> roleQuery) {
        return null;
    }

    @Override
    public boolean addRwRolePermission(RwRolePermission permission) {
        int ret = rwRolePermissionMapper.insertSelective(permission);
        if (ret > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRwRolePermissionById(RwRolePermission permission) {
        return false;
    }

    @Override
    public RwPermission getRwRolePermissionById(Integer id) {
        return null;
    }

    @Override
    public boolean getByPidAndLevel(Integer pid, Integer level) {
        RwRolePermission rolePermission = rwRolePermissionMapper.selectByPidAndLevel(pid*1l,level*1l);
        if(Objects.isNull(rolePermission)){
            return false;
        }
        return true;
    }
}
