package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.RSACoder;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingwu on 2017/5/24.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private IRwAppMemberService rwAppMemberService;

    /**
     * 转化成JSON格式
     *
     * @param object 参数
     * @return 返回JSON字符串
     */
    public String toJSON(Object object) {
        return JSONObject.toJSONString(object);
    }

    /**
     * 检查AppId和DeskKey是否为空
     *
     * @param appId
     * @param deskKey
     * @return
     */
    public String checkAppIdAndDeskKey(String appId, String deskKey) {
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
                String publicKey = deskKey.replace(" ","");
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
                logger.info("AppId : "+ appId+ " 验签状态 : " + status);
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
}
