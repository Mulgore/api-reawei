package cn.reawei.api.controller.setting;

import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwPermission;
import cn.reawei.api.service.IRwPermissionService;
import cn.reawei.common.page.OrderBy;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1")
public class PermissionController extends BaseController {

    @Resource
    IRwPermissionService rwPermissionService;

    @RequestMapping(value = "settingPermission", method = RequestMethod.GET)
    public String getList() {
        Query<RwPermission> permissionQuery = getQuery();
        RwPermission permission = new RwPermission();
        String state = request.getParameter("state");
        String title = request.getParameter("title");
        if (!Objects.isNull(state)) {
            permission.setState(Integer.parseInt(state));
        }
        if (!Objects.isNull(title)) {
            permission.setTitle(title.replaceAll(" ", ""));
        }
        permission.setPid(0l);
        OrderBy orderBy = new OrderBy();
        permissionQuery.setOrderBy(orderBy);
        permissionQuery.setQueryObject(permission);
        Result<RwPermission> result = rwPermissionService.getResultByQuery(permissionQuery);
        return jsonPageResult(result);
    }

    @RequestMapping(value = "settingPermission/{id}", method = RequestMethod.DELETE)
    public String deletePerm(@PathVariable Integer id) {
        boolean rlt = rwPermissionService.deleteById(id);
        if (!rlt) {
            return callbackFail("删除失败！");
        }
        return callbackSuccess("权限删除成功");
    }


    @RequestMapping(value = "settingPermission", method = RequestMethod.POST)
    public String createAndUpdate(RwPermission permission) {
        if (Objects.isNull(permission)) {
            return callbackFail("参数不能为空");
        }
        if (Objects.isNull(permission.getPid())) {
            permission.setPid(0l);
        }
        if (Objects.isNull(permission.getId())) {
            rwPermissionService.addRwPermission(permission);
            return callbackSuccess("菜单添加成功");
        } else {
            rwPermissionService.updateRwPermissionById(permission);
            return callbackSuccess("权限菜单修改成功");
        }
    }

    @RequestMapping(value = "settingPermission/child/{id}", method = RequestMethod.GET)
    public String getChildList(@PathVariable Integer id) {
        Query<RwPermission> permissionQuery = getQuery();
        RwPermission permission = new RwPermission();
        String state = request.getParameter("state");
        String title = request.getParameter("title");
        if (!Objects.isNull(state)) {
            permission.setState(Integer.parseInt(state));
        }
        if (!Objects.isNull(title)) {
            permission.setTitle(title.replaceAll(" ", ""));
        }
        permission.setPid(id * 1l);
        OrderBy orderBy = new OrderBy();
        permissionQuery.setOrderBy(orderBy);
        permissionQuery.setQueryObject(permission);
        Result<RwPermission> result = rwPermissionService.getResultByQuery(permissionQuery);
        return jsonPageResult(result);
    }

    @RequestMapping(value = "settingPermission/state/child", method = RequestMethod.POST)
    public String removeChild(Integer id, String state) {
        RwPermission permission = rwPermissionService.getRwPermissionById(id);
        permission.setState(Integer.parseInt(state));
        rwPermissionService.updateRwPermissionById(permission);
        if (state.equals("0")) {
            return callbackSuccess("禁用成功");
        } else {
            return callbackSuccess("启用成功");
        }
    }

}
