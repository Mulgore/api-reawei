package cn.reawei.api.controller.sys;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.RSACoder;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

public class BaseController extends SuperController implements HandlerInterceptor {

    @Resource
    private IRwAppMemberService rwAppMemberService;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * <p>
     * 该方法返回true，才会继续执行后续的Interceptor和Controller
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        SSOToken ssoToken = SSOHelper.getSSOToken(httpServletRequest);
        if (ssoToken == null) {
            switch (httpServletRequest.getRequestURI()) {
                case "/api/v1/user":
                    return true;
                case "/api/v1/user/login":
                    return true;
                case "/api/v1/user/sendSms":
                    return true;
            }
            return false;
        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后)
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        String  callback = httpServletRequest.getParameter("callback");
//        String jsoncallback = callback + "({'result':})";
//        PrintWriter out =  httpServletResponse.getWriter();
//        out.print(jsoncallback);
//        out.flush();
//        out.close();
    }

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
