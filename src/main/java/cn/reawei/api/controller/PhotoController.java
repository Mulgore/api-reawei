package cn.reawei.api.controller;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xingwu on 2017/5/26.
 */
@RestController
@RequestMapping("/v1/photo")
public class PhotoController extends BaseController {


    @ResponseBody
    @RequestMapping(value = "/result/**", method = RequestMethod.GET)
    public String equals(String appId, HttpServletRequest request) {
        String path = request.getServletPath();
        String deskKey = path.substring(path.indexOf("result/") + 7, path.lastIndexOf("."));
        Map<String, Object> ret = new HashMap<>();
        String checkStr = checkAppIdAndDeskKey(appId, deskKey);
        if (StringUtil.isNotBlank(checkStr) && !"{}".equals(checkStr)) {
            return checkStr;
        }
        ret.put("code",0);
        List<String> result = new ArrayList<>();
        result.add("http://pic.58pic.com/58pic/17/41/38/88658PICNuP_1024.jpg");
        result.add("http://pic76.nipic.com/file/20150825/11284670_155836545000_2.jpg");
        result.add("http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg");
        result.add("http://img06.tooopen.com/images/20160712/tooopen_sy_170083325566.jpg");
        result.add("http://p4.gexing.com/shaitu/2011/11/12/14074ebe0d2bc101f.jpg");
        result.add("http://pic44.nipic.com/20140717/12432466_121957328000_2.jpg");
        result.add("http://pic.58pic.com/58pic/16/01/58/80F58PICvCc_1024.jpg");
        result.add("http://pic.58pic.com/58pic/13/20/61/89B58PIC5Nz_1024.jpg");
        result.add("http://pic.58pic.com/58pic/13/19/66/66H58PICcnt_1024.jpg");
        result.add("http://pic.58pic.com/58pic/13/04/21/31D58PICVAH.jpg");
        result.add("http://pic.58pic.com/58pic/11/34/45/97E58PICIti.jpg");
        ret.put("data",result);
        return toJSON(ret);
    }
}
