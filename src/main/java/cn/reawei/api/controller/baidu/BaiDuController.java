package cn.reawei.api.controller.baidu;

import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.common.enums.BaiDu;
import cn.reawei.common.utils.BaiDuUtil;
import cn.reawei.common.utils.ReadExcelUtils;
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

//    public void updateCode() {
//        try {
//            String filepath = "/Users/xingwu/Downloads/code.xlsx";
//            ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
//            Map<Integer, Map<Integer, Object>> map = excelReader.readExcelContent();
//            PinYinUtils yinUtils = new PinYinUtils();
//            System.out.println("获得Excel表格的内容:");
//            for (int i = 1; i <= map.size(); i++) {
//                Map<Integer, Object> data = map.get(i);
//                System.out.println(data.toString());
//                if (StringUtil.isBlank(data.get(4).toString()) && StringUtil.isBlank(data.get(5).toString())) {
//                    BaseProvince checkProvince = addressService.getProvinceByProvinceCode(data.get(6).toString().substring(0, data.get(6).toString().indexOf(".")));
//                    if (Objects.isNull(checkProvince)) {
//                        BaseProvince province = new BaseProvince();
//                        province.setProvincename(data.get(1).toString());
//                        province.setProvinceename(yinUtils.getStringPinYin(data.get(1).toString()));
//                        province.setProvincecode(data.get(6).toString().substring(0, data.get(6).toString().indexOf(".")));
//                        province.setLnglat(null);
//                        addressService.addProvince(province);
//                    }
//                    continue;
//                }
//                if (StringUtil.isBlank(data.get(5).toString())) {
//                    BaseCity checkCity = addressService.getCityByCityCode(data.get(6).toString().substring(0, data.get(6).toString().indexOf(".")));
//                    if (Objects.isNull(checkCity)) {
//                        BaseCity city = new BaseCity();
//                        city.setCityname(data.get(2).toString());
//                        city.setCityename(yinUtils.getStringPinYin(data.get(2).toString()));
//                        city.setCitycode(data.get(6).toString().substring(0, data.get(6).toString().indexOf(".")));
//                        city.setParentcode(data.get(4).toString().substring(0, data.get(4).toString().indexOf(".")));
//                        city.setLnglat(null);
//                        addressService.addCity(city);
//                    }
//                    continue;
//                } else if (StringUtil.isNotBlank(data.get(5).toString())) {
//                    BaseCity checkCity = addressService.getCityByCityCode(data.get(5).toString().substring(0, data.get(5).toString().indexOf(".")));
//                    if (Objects.isNull(checkCity)) {
//                        BaseCity city = new BaseCity();
//                        city.setCityname(data.get(2).toString());
//                        city.setCityename(yinUtils.getStringPinYin(data.get(2).toString()));
//                        city.setCitycode(data.get(5).toString().substring(0, data.get(5).toString().indexOf(".")));
//                        city.setParentcode(data.get(4).toString().substring(0, data.get(4).toString().indexOf(".")));
//                        city.setLnglat(null);
//                        addressService.addCity(city);
//                    }
//                }
//                if (StringUtil.isNotBlank(data.get(6).toString())) {
//                    BaseArea checkArea = addressService.getAreaByAreaCode(data.get(6).toString().substring(0, data.get(6).toString().indexOf(".")));
//                    if (Objects.isNull(checkArea)) {
//                        BaseArea area = new BaseArea();
//                        area.setAreaname(data.get(3).toString());
//                        area.setAreaename(yinUtils.getStringPinYin(data.get(3).toString()));
//                        area.setAreacode(data.get(6).toString().substring(0, data.get(6).toString().indexOf(".")));
//                        area.setParentcode(data.get(5).toString().substring(0, data.get(5).toString().indexOf(".")));
//                        area.setLnglat(null);
//                        addressService.addArea(area);
//                    }
//
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("未找到指定路径的文件!");
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
