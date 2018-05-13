package cn.reawei.api.controller.lucky;

import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.common.utils.HttpsUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created in 2018/5/11 09:14
 *
 * @author qigong
 */
@RestController
@RequestMapping("/api/v1")
public class LuckyController extends BaseController {

    @Permission(action = Action.Skip)
    @RequestMapping(value = "lucky", method = RequestMethod.POST)
    public String getLucky(@RequestBody JSONObject json) {
        Map<String, Object> params = new HashMap<>(16);
        Map<String, String> headers = new HashMap<>(16);
        String token = "A9FA5907221F2471A8FA893D4A642FA5BFFDCB65B68EEE5B61EBE83F5F072E83729E2B03030DF126479E59804CD902B66441999AC6E08BBE36328E11329D9FD8";
        headers.put("x-user-token", token);
        String mobile = json.getString("mobile");
        String url = json.getString("url");
        params.put("url", url);
        params.put("phone", mobile);
        String urlApi = "https://api.mtdhb.com/user/receiving";
        return callbackSuccess(HttpsUtil.doPost(urlApi, params, headers, "UTF-8"));
    }

}
