package cn.reawei.api.controller.account;

import cn.reawei.api.controller.sys.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.DATE;

@RestController
@RequestMapping("/api/v1")
public class AccountController extends BaseController {

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 主页
     *
     * @return 返回JSON格式的字符串
     */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String login() {
        Map<String, Object> data = new HashMap<>();
        //上周交易额
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd");
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        List<Map<String, Object>> number = new ArrayList<>();
        Map<String, Object> number1 = new HashMap<>();
        number1.put("icon", "pay-circle-o");
        number1.put("color", "#948aec");
        number1.put("title", "本周交易");
        number1.put("day", "总数");
        number1.put("decimals", 2);
        number1.put("number", Double.parseDouble(decimalFormat.format(Math.random()*5000)));
        Map<String, Object> number2 = new HashMap<>();
        number2.put("icon", "pay-circle-o");
        number2.put("color", "#76d0a3");
        number2.put("title", "本周分润");
        number2.put("day", "总数");
        number2.put("decimals", 2);
        number2.put("number", Double.parseDouble(decimalFormat.format(Math.random()*5000)));
        Map<String, Object> number3 = new HashMap<>();
        number3.put("icon", "heart-o");
        number3.put("color", "#f46e65");
        number3.put("title", "平台会员");
        number3.put("day", "总数");
        number3.put("decimals", 0);
        number3.put("number", Math.round(Math.random()*5000));
        Map<String, Object> number4 = new HashMap<>();
        number4.put("icon", "user");
        number4.put("color", "#f7629e");
        number4.put("title", "访问用户");
        number4.put("day", "总数");
        number4.put("decimals", 0);
        number4.put("number", Math.round(Math.random()*5000));
        number.add(number1);
        number.add(number2);
        number.add(number3);
        number.add(number4);
        List<Map<String, Object>> salesList = new ArrayList<>();
        for (int i = -7; i < 0; i++) {
            Map<String, Object> sales = new HashMap<>();
            Calendar calendar = Calendar.getInstance();
            calendar.add(DATE,i);

            sales.put("name",dateFormat.format(calendar.getTime()));
            sales.put("交易金额", Double.parseDouble(decimalFormat.format(Math.random()*5000)));
            sales.put("交易笔数", Math.round(Math.random()*2000));
            salesList.add(sales);
        }
        data.put("numbers", number);
        data.put("sales", salesList);
        return callbackSuccess(data);
    }
}
