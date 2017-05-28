package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwMember;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xingwu on 2017/5/24.
 */
@Service
public interface IRwMemberService {

    List<RwMember> getRwMemberResult(Query<RwMember> memberQuery);

    RwMember getRwMemberById(Long memberId);
}
