package cn.reawei.api.controller.sys;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.RSACoder;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Controller的作用是: 这是Controller的拦截器
 * <p>
 * Created by xingwu on 2017/5/24.
 */

public class BaseController extends SuperController {

    @Resource
    private IRwAppMemberService rwAppMemberService;

    /**
     * 检查AppId和DeskKey是否为空，验证公私钥
     *
     * @param appId
     * @return
     */
    protected boolean checkAppIdAndDeskKeyPermission(String appId, String apiId, Map<String, Object> ret) {
        String path = this.request.getServletPath();
        String deskKey = path.substring(path.indexOf("result/") + 7, path.lastIndexOf("."));

        boolean rlt = false;
        //判断公钥与AppId是否为空
        if (StringUtils.isBlank(appId) && StringUtils.isBlank(deskKey)) {
            ret.put("code", Constants.CODE_ERROR_APP_ID_AND_DESK_KEY_NULL);
            ret.put("msg", "AppId和DeskKey为空!!!");
            return true;
        }
        if (StringUtils.isBlank(deskKey)) {
            ret.put("code", Constants.CODE_ERROR_DESK_KEY_NULL);
            ret.put("msg", "DeskKey为空!!!");
            return true;
        }
        if (StringUtils.isBlank(appId)) {
            ret.put("code", Constants.CODE_ERROR_APP_ID_NULL);
            ret.put("msg", "AppId为空!!!");
            return true;
        }
        RwAppMember appMember = rwAppMemberService.getAppMemberById(Long.parseLong(appId));
        // 验证appId调用权限
        if (appMember == null) {
            ret.put("code", Constants.CODE_ERROR_APP_ID_NOT_PERM);
            ret.put("msg", "AppId没有权限!!!");
            return true;
        }
        if (!apiId.equals(appMember.getApiId().toString())) {
            ret.put("code", Constants.CODE_ERROR_APP_ID_NOT_PERM);
            ret.put("msg", "AppId没有权限!!!");
            return true;
        }
        if (!"0".equals(appMember.getStatus().toString())) {
            ret.put("code", Constants.CODE_ERROR_APP_ID_NOT_ENABLED);
            ret.put("msg", "接口未启用!!!");
            return true;
        }
//        验证公钥
        if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(deskKey)) {
            boolean status = false;
            try {
                String privateKey = appMember.getPrivateKey();
                String publicKey = deskKey.replace(" ", "");
                String inputStr = "";
                byte[] data = inputStr.getBytes();

                byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
//                byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);
//                String outputStr = new String(decodedData);
                // 产生签名
                String sign = RSACoder.sign(encodedData, privateKey);
                // 验证签名
                status = RSACoder.verify(encodedData, publicKey, sign);
                logger.info("AppId : " + appId + " 验签状态 : " + status);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("------- { appId= " + appId + " deskKey= " + deskKey + " Exception " + e.getMessage() + " } ---------");
            } finally {
                if (!status) {
                    ret.put("code", Constants.CODE_ERROR_APP_ID_NOT_PERM);
                    ret.put("msg", "验证签名失败!!!");
                    rlt = true;
                }
            }
        }

        // 验证用户等级与可调用次数限制
        switch (appMember.getLevel()) {
            case 0:
                if (appMember.getNumberTotal() >= 500) {
                    ret.put("code", Constants.CODE_ERROR_TOTAL_NUMBER_MAX);
                    ret.put("msg", "接口调用上限！");
                    return true;
                }
                break;
            case 1:
                if (appMember.getNumberTotal() >= 2000) {
                    ret.put("code", Constants.CODE_ERROR_TOTAL_NUMBER_MAX);
                    ret.put("msg", "接口调用上限！");
                    return true;
                }
                break;
            case 2:
                if (appMember.getNumberTotal() >= 5000) {
                    ret.put("code", Constants.CODE_ERROR_TOTAL_NUMBER_MAX);
                    ret.put("msg", "接口调用上限！");
                    return true;
                }
                break;
            case 3:
                if (appMember.getNumberTotal() >= 10000) {
                    ret.put("code", Constants.CODE_ERROR_TOTAL_NUMBER_MAX);
                    ret.put("msg", "接口调用上限！");
                    return true;
                }
        }
        // 接口调用次数 + 1
        appMember.setNumberTotal(appMember.getNumberTotal() + 1);
        rwAppMemberService.updateAppMemberById(appMember);
        return rlt;
    }


}
