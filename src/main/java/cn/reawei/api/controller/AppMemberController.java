package cn.reawei.api.controller;

import cn.reawei.api.common.Constants;
import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.common.utils.RSACoder;
import cn.reawei.api.controller.sys.BaseController;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Controller的作用是: 接口权限
 * <p>
 * Created by xingwu on 2017/5/26.
 */
@RestController
@RequestMapping("/v1/app/member")
public class AppMemberController extends BaseController {

    @Resource
    private IRwAppMemberService rwAppMemberService;

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 申请接口权限
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveAppMember(Long memberId) {
        Map<String, Object> data = new HashMap<>();
        if (memberId == null) {
            data.put("code", Constants.CODE_APP_MEMBER_MEMBER_ID_IS_ERROR);
            data.put("data", false);
            return toJSON(data);
        }
        RwAppMember appMember = new RwAppMember();
        appMember.setMemberId(memberId);

        boolean ret = false;
        try {
            Map<String, Object> keyMap = RSACoder.initKey();
            appMember.setPublicKey(RSACoder.getPublicKey(keyMap));
            appMember.setPrivateKey(RSACoder.getPrivateKey(keyMap));
            System.out.println(RSACoder.getPublicKey(keyMap));
            System.out.println(RSACoder.getPrivateKey(keyMap));

        } catch (Exception e) {
            e.printStackTrace();
            if (StringUtil.isNotBlank(e.getMessage())) {
                return toJSON("生成秘钥失败！");
            }
        }
        int rlt = rwAppMemberService.saveAppMember(appMember);
        if (rlt > 0) {
            ret = true;
        }
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 删除接口权限
     *
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public String removeAppMember(Long appMemberId) {
        Map<String, Object> ret = new HashMap<>();
        int rlt = rwAppMemberService.deleteAppMemberById(appMemberId);
        ret.put("code", 0);
        ret.put("data", true);
        if (rlt == 0) {
            ret.put("code", Constants.CODE_APP_MEMBER_REMOVE_IS_ERROR);
            ret.put("data", false);
        }
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 根据接口权限修改接口信息
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public String updateAppMember(RwAppMember appMember) {
        Map<String, Object> ret = new HashMap<>();
        int rlt = rwAppMemberService.updateAppMemberById(appMember);
        ret.put("code", 0);
        ret.put("data", true);
        if (rlt == 0) {
            ret.put("code", Constants.CODE_APP_MEMBER_REMOVE_IS_ERROR);
            ret.put("data", false);
        }
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 根据接口权限修改接口信息
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String infoAppMember(Long appMemberId) {
        Map<String, Object> ret = new HashMap<>();
        RwAppMember rwAppMember = rwAppMemberService.getAppMemberById(appMemberId);
        ret.put("code", 0);
        ret.put("data", true);
        if (rwAppMember == null) {
            ret.put("code", Constants.CODE_APP_MEMBER_REMOVE_IS_ERROR);
            ret.put("data", false);
            return toJSON(ret);
        }
        ret.put("result", rwAppMember);
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 根据会员Id查询会员的接口权限Result
     *
     * @return
     */
    @RequestMapping(value = "/member/results/{memberId}", method = RequestMethod.GET)
    public String getAppMemberResultByMemberId(@PathVariable Long memberId) {
        Map<String, Object> ret = new HashMap<>();
        if (memberId == null) {
            ret.put("code", Constants.CODE_APP_MEMBER_MEMBER_RESULT_IS_ERROR);
            ret.put("data", false);
            return toJSON(ret);
        }
        Query<RwAppMember> memberQuery = getQuery();
        RwAppMember appMember = new RwAppMember();
        appMember.setMemberId(memberId);
        memberQuery.setQueryObject(appMember);
        Result<RwAppMember> results = rwAppMemberService.getAppMemberResultByQuery(memberQuery);
        ret.put("code", 0);
        ret.put("data", true);
        if (results == null) {
            ret.put("code", Constants.CODE_APP_MEMBER_REMOVE_IS_ERROR);
            ret.put("data", false);
            return toJSON(ret);
        }
        ret.put("result", results);
        return toJSON(ret);
    }

    /**
     * 致终于来到这里的勇敢的人：
     * <p>
     * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
     * <p>
     * 嗯！好了这个接口的作用是: 获取接口权限Result
     *
     * @return
     */
    @RequestMapping(value = "/member/results", method = RequestMethod.GET)
    public String getAppMemberResult() {
        Map<String, Object> ret = new HashMap<>();
        Query<RwAppMember> memberQuery = getQuery();
        RwAppMember appMember = new RwAppMember();
        memberQuery.setQueryObject(appMember);
        Result<RwAppMember> results = rwAppMemberService.getAppMemberResultByQuery(memberQuery);
        ret.put("code", 0);
        ret.put("data", true);
        if (results == null) {
            ret.put("code", Constants.CODE_APP_MEMBER_REMOVE_IS_ERROR);
            ret.put("data", false);
            return toJSON(ret);
        }
        ret.put("result", results);
        return toJSON(ret);
    }
}
