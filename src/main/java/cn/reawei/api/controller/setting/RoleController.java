package cn.reawei.api.controller.setting;

import cn.reawei.api.common.utils.Page.OrderBy;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwRole;
import cn.reawei.api.service.IRwRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "api/v1")
public class RoleController extends BaseController {

    @Resource
    IRwRoleService rwRoleService;

    @RequestMapping(value = "settingRole",method = RequestMethod.GET)
    public String getRoleList(){
        Query<RwRole> roleQuery = new Query<>();
        OrderBy orderBy = new OrderBy();
        orderBy.setDesc(false);
        orderBy.setFieldName("sort");
        roleQuery.setOrderBy(orderBy);
        Result<RwRole> result = rwRoleService.getResultByQuery(roleQuery);
        return jsonPageResult(result);
    }
}
