package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwAppMemberMapper;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwAppMemberService")
@Transactional
public class IRwAppMemberServiceImpl implements IRwAppMemberService {

    @Resource
    private RwAppMemberMapper rwAppMemberMapper;

    @Override
    public RwAppMember getAppMemberById(Long id) {
        return rwAppMemberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveAppMember(RwAppMember appMember) {
        return rwAppMemberMapper.insertSelective(appMember);
    }

    @Override
    public int updateAppMemberById(RwAppMember appMember) {
        return rwAppMemberMapper.updateByPrimaryKeySelective(appMember);
    }

    @Override
    public int deleteAppMemberById(Long id) {
        return rwAppMemberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Result<RwAppMember> getAppMemberResultByQuery(Query<RwAppMember> memberQuery) {
        Result<RwAppMember> result = new Result<>();
        result.setDataList(rwAppMemberMapper.selectResultByQuery(memberQuery));
        result.setTotal(rwAppMemberMapper.countResultByQuery(memberQuery));
        return result;
    }
}
