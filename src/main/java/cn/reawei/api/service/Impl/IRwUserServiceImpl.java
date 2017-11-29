package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwUserMapper;
import cn.reawei.api.model.RwUser;
import cn.reawei.api.service.IRwUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("rwUserService")
public class IRwUserServiceImpl implements IRwUserService {

    @Resource
    private RwUserMapper rwUserMapper;

    @Override
    @Transactional
    public RwUser getUserInfoByLoginName(String loginName) {
        return rwUserMapper.selectByLoginName(loginName);
    }

    @Override
    @Transactional
    public RwUser getUserInfoById(Integer id) {
        return rwUserMapper.selectByPrimaryKey(id.longValue());
    }

    @Override
    @Transactional
    public boolean updateUserById(RwUser user) {
        int ret = rwUserMapper.updateByPrimaryKeySelective(user);
        if(ret > 0){
            return true;
        }
        return false;
    }
}
