package cn.reawei.api.controller.lucky;

import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.common.utils.DateUtil;
import cn.reawei.common.utils.HttpsUtil;
import cn.reawei.common.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created in 2018/5/11 09:14
 *
 * @author qigong
 */
@RestController
@RequestMapping("/api/v1")
public class LuckyController extends BaseController {

    private static String DATE_DAY = "";
    private static Integer Lucky_Number = 0;

    @Permission(action = Action.Skip)
    @RequestMapping(value = "lucky", method = RequestMethod.POST)
    public String getLucky(@RequestBody JSONObject json) {
        Integer index = 20;
        String regText1 = "https";
        String regText2 = "ele";
        String regText3 = "hongbao";
        String regText4 = "lucky_number";
        Map<String, Object> params = new HashMap<>(16);
        Map<String, String> headers = new HashMap<>(16);
        String token = "A9FA5907221F2471A8FA893D4A642FA5BFFDCB65B68EEE5B61EBE83F5F072E83729E2B03030DF126479E59804CD902B66441999AC6E08BBE36328E11329D9FD8";
        headers.put("x-user-token", token);
        String mobile = json.getString("mobile");
        String url = json.getString("url");
        if (!url.contains(regText1) || !url.contains(regText2) || !url.contains(regText3) || !url.contains(regText4)) {
            return callbackFail("谢谢惠顾");
        }
        if (StringUtil.isBlank(DATE_DAY)) {
            DATE_DAY = DateUtil.dateToStr(new Date());
        }
        if (Objects.equals(DATE_DAY, DateUtil.dateToStr(new Date()))) {
            Lucky_Number++;
        } else {
            Lucky_Number = 0;
            DATE_DAY = DateUtil.dateToStr(new Date());
        }
        if (Lucky_Number > index) {
            return callbackFail("次数上限");
        }
        params.put("url", url);
        params.put("phone", mobile);
        String urlApi = "https://api.mtdhb.com/user/receiving";
        return callbackSuccess(HttpsUtil.doPost(urlApi, params, headers, "UTF-8"));
    }
}
