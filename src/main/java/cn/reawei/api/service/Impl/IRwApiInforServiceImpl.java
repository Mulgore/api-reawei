package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwApiInfoMapper;
import cn.reawei.api.model.RwApiInfo;
import cn.reawei.api.service.IRwApiInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwApiInfoService")
public class IRwApiInforServiceImpl implements IRwApiInfoService {

    @Resource
    private RwApiInfoMapper rwApiInfoMapper;

    @Override
    public RwApiInfo getAppMemberById(Long id) {
        return rwApiInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveAppMember(RwApiInfo apiInfo) {
        return rwApiInfoMapper.insertSelective(apiInfo);
    }

}
