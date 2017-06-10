package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwUserMapper;
import cn.reawei.api.model.RwUser;
import cn.reawei.api.service.IRwUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("rwUserService")
public class IRwUserServiceImpl implements IRwUserService {

    @Resource
    private RwUserMapper rwUserMapper;

    @Override
    public RwUser getUserInfoByLoginName(String loginName) {
        return rwUserMapper.selectByLoginName(loginName);
    }
}
