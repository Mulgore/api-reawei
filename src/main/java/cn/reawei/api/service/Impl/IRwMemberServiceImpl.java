package cn.reawei.api.service.Impl;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.mapper.RwMemberMapper;
import cn.reawei.api.model.RwMember;
import cn.reawei.api.service.IRwMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwMemberService")
public class IRwMemberServiceImpl implements IRwMemberService {


    @Autowired
    private RwMemberMapper rwMemberMapper;

    @Override
    @Transactional
    public List<RwMember> getRwMemberResult(Query<RwMember> memberQuery) {
        return null;
    }

    @Override
    @Transactional
    public RwMember getRwMemberById(Long memberId) {
        return rwMemberMapper.selectByPrimaryKey(memberId);
    }
}
