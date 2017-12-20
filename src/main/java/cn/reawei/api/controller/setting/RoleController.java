package cn.reawei.api.controller.setting;

import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwPermission;
import cn.reawei.api.model.RwRole;
import cn.reawei.api.model.RwRolePermission;
import cn.reawei.api.service.IRwPermissionService;
import cn.reawei.api.service.IRwRolePermissionService;
import cn.reawei.api.service.IRwRoleService;
import cn.reawei.common.page.OrderBy;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1")
public class RoleController extends BaseController {

    @Resource
    IRwRoleService rwRoleService;
    @Resource
    IRwPermissionService rwPermissionService;
    @Resource
    IRwRolePermissionService rwRolePermissionService;

    @Permission(action = Action.Skip)
    @RequestMapping(value = "settingRole", method = RequestMethod.GET)
    public String getRoleList() {
        Query<RwRole> roleQuery = getQuery();
        RwRole rwRole = new RwRole();
        String title = request.getParameter("title");
        if (!Objects.isNull(title)) {
            rwRole.setName(title);
        }
        OrderBy orderBy = new OrderBy();
        orderBy.setDesc(false);
        orderBy.setFieldName("sort");
        roleQuery.setOrderBy(orderBy);
        roleQuery.setQueryObject(rwRole);
        Result<RwRole> result = rwRoleService.getResultByQuery(roleQuery);
        return jsonPageResult(result);
    }

    @Permission(action = Action.Skip)
    @RequestMapping(value = "settingRole", method = RequestMethod.POST)
    public String addAndUpdate(RwRole rwRole) {
        if (Objects.isNull(rwRole)) {
            return callbackFail("参数不能为空");
        }
        if (Objects.isNull(rwRole.getId())) {
            boolean check = rwRoleService.getCheckByLevel(rwRole.getSort());
            if (check) {
                return callbackFail("角色等级已存在");
            }
            rwRoleService.addRwRole(rwRole);
            return callbackSuccess("角色添加成功");
        } else {
            rwRoleService.updateRwRoleById(rwRole);
            return callbackSuccess("角色修改成功");
        }
    }

    @Permission(action = Action.Skip)
    @RequestMapping(value = "settingRole/{id}", method = RequestMethod.DELETE)
    public String deletePerm(@PathVariable Integer id) {
        boolean rlt = rwRoleService.deleteById(id);
        if (!rlt) {
            return callbackFail("删除失败！");
        }
        return callbackSuccess("角色删除成功");
    }

    @Permission(action = Action.Skip)
    @RequestMapping(value = "settingRole/perm", method = RequestMethod.GET)
    public String getPermList(Integer level) {
        Query<RwPermission> permissionQuery = getQuery();
        RwPermission permission = new RwPermission();
        permission.setState(1);
        permissionQuery.setQueryObject(permission);
        Result<RwPermission> result = rwPermissionService.getResultByQuery(permissionQuery);
        for (RwPermission perm : result.getDataList()) {
            boolean check = rwRolePermissionService.getByPidAndLevel(perm.getId().intValue(), level);
            perm.setState(0);
            if(check){
                perm.setState(1);
            }
        }
        return jsonPageResult(result);
    }

    @Permission(action = Action.Skip)
    @RequestMapping(value = "settingRole/perm/status", method = RequestMethod.POST)
    public String removeChild(Integer level, Integer id, Integer type) {
        switch (type) {
            case 1:
                RwRolePermission permission = new RwRolePermission();
                permission.setPid(id * 1l);
                permission.setRid(level * 1l);
                rwRolePermissionService.addRwRolePermission(permission);
                return callbackSuccess("启用成功");
            case 0:
                rwRolePermissionService.deleteByPidAndLevel(id, level);
                return callbackSuccess("禁用成功");
        }
        return callbackSuccess("系统异常");
    }
}
