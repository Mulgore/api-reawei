package cn.reawei.api.controller.baidu;

import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.common.enums.BaiDu;
import cn.reawei.common.utils.BaiDuUtil;
import cn.reawei.common.utils.StringUtil;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Date: Created in 2018/2/10 14:44
 * @Author: xingwu
 */
@RestController
@RequestMapping("api/v1")
public class BaiDuController extends BaseController {

    @Permission(action = Action.Skip)
    @RequestMapping(value = "/getlngat", method = RequestMethod.GET)
    public String getLngat(String address) {
        if (StringUtil.isBlank(address)) return callbackSuccess("地址不能为空");
        Map<String, Object> result = BaiDuUtil.getLngLatByAddress(BaiDu.BAI_DU_AK.key, address);
        return callbackSuccess(result);
    }
}
