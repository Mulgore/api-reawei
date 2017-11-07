package cn.reawei.api.controller.setting;

import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwRole;
import cn.reawei.api.service.IRwRoleService;
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

    @RequestMapping(value = "settingRole", method = RequestMethod.GET)
    public String getRoleList() {
        Query<RwRole> roleQuery = new Query<>();
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

    @RequestMapping(value = "settingRole/{id}", method = RequestMethod.DELETE)
    public String deletePerm(@PathVariable Integer id) {
        boolean rlt = rwRoleService.deleteById(id);
        if (!rlt) {
            return callbackFail("删除失败！");
        }
        return callbackSuccess("角色删除成功");
    }
}
