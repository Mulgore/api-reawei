package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.AjaxResult;
import cn.reawei.api.common.utils.RSACoder;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingwu on 2017/5/24.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Resource
    private IRwAppMemberService rwAppMemberService;

    /**
     * 转化成JSON格式
     *
     * @param object 参数
     * @return 返回JSON字符串
     */
    protected String toJSON(Object object) {
        return JSONObject.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * 返回 JSON 格式对象
     *
     * @param object 转换对象
     * @param format 序列化特点
     * @return
     */
    protected String toJSON(Object object, String format) {
        if (format == null) {
            return toJSON(object);
        }
        return JSONObject.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * 检查AppId和DeskKey是否为空，验证公私钥
     *
     * @param appId
     * @param deskKey
     * @return
     */
    protected String checkAppIdAndDeskKey(String appId, String deskKey) {
        Map<String, Object> ret = new HashMap<>();
        if (StringUtil.isBlank(deskKey)) {
            ret.put("code", Constants.PHOTO_CODE_ERROR_DESK_KEY_NULL);
            ret.put("msg", "DeskKey为空!!!");
        }
        if (StringUtil.isBlank(appId)) {
            ret.put("code", Constants.PHOTO_CODE_ERROR_APP_ID_NULL);
            ret.put("msg", "AppId为空!!!");
        }
        if (StringUtil.isBlank(appId) && StringUtil.isBlank(deskKey)) {
            ret.put("code", Constants.PHOTO_CODE_ERROR_APP_ID_AND_DESK_KEY_NULL);
            ret.put("msg", "AppId和DeskKey为空!!!");
        }
        if (StringUtil.isNotBlank(appId) && StringUtil.isNotBlank(deskKey)) {
            RwAppMember appMember = rwAppMemberService.getAppMemberById(Long.parseLong(appId));
            if (appMember == null) {
                ret.put("code", Constants.PHOTO_CODE_ERROR_APP_ID_NOT_PERM);
                ret.put("msg", "AppId没有权限!!!");
                toJSON(ret);
            }

            boolean status = false;
            try {
                String privateKey = appMember.getPrivateKey();
                String publicKey = deskKey.replace(" ", "");
                String inputStr = "";
                byte[] data = inputStr.getBytes();

                byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
//
//                byte[] decodedData = RSACoder
//                        .decryptByPublicKey(encodedData, publicKey);
//
//                String outputStr = new String(decodedData);
                // 产生签名
                String sign = RSACoder.sign(encodedData, privateKey);
//                System.err.println("签名:\r" + sign);

                // 验证签名
                status = RSACoder.verify(encodedData, publicKey, sign);
                logger.info("AppId : " + appId + " 验签状态 : " + status);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!status) {
                    ret.put("code", Constants.PHOTO_CODE_ERROR_APP_ID_NOT_PERM);
                    ret.put("msg", "验证签名失败!!!");
                }
            }
        }
        return toJSON(ret);
    }

    /**
     * <p>
     * 自动判定是否有跨域操作,转成字符串并返回
     * </p>
     *
     * @param object
     * @return 跨域或不跨域的字符串
     */
    protected String callback(AjaxResult object) {
        return callback(object, null);
    }

    protected String callback(AjaxResult object, String format) {
        String callback = request.getParameter("callback");
        if (callback == null) {
            /**
             * 非 JSONP 请求
             */
            return toJSON(object, format);
        }
        StringBuffer json = new StringBuffer();
        json.append(callback);
        json.append("(").append(toJSON(object, format)).append(")");
        return json.toString();
    }

    protected String callbackSuccess(Object obj) {
        return callback(new AjaxResult(obj));
    }

    protected String callbackFail(String message) {
        return callback(new AjaxResult(false, message));
    }

}
