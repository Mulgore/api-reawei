package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwAppMemberMapper;
import cn.reawei.api.model.RwAppMember;
import cn.reawei.api.service.IRwAppMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwAppMemberService")
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

}
