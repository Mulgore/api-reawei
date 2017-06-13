package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwMember;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个接口的作用是: 对会员，CRUD操作
 * <p>
 * Created by xingwu on 2017/6/8.
 */
@Service
public interface IRwMemberService {

    List<RwMember> getRwMemberResult(Query<RwMember> memberQuery);

    RwMember getRwMemberById(Long memberId);
}
