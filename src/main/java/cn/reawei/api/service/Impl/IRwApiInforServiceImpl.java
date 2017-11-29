package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwApiInfoMapper;
import cn.reawei.api.model.RwApiInfo;
import cn.reawei.api.service.IRwApiInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwApiInfoService")
@Transactional
public class IRwApiInforServiceImpl implements IRwApiInfoService {

    @Resource
    private RwApiInfoMapper rwApiInfoMapper;

    @Override
    @Transactional
    public RwApiInfo getAppInfoById(Long id) {
        return rwApiInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int saveAppInfo(RwApiInfo apiInfo) {
        return rwApiInfoMapper.insertSelective(apiInfo);
    }

}
