package cn.reawei.api.service.Impl;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.mapper.RwPhotoInfoMapper;
import cn.reawei.api.model.RwPhotoInfo;
import cn.reawei.api.service.IRwPhotoInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwPhotoInfoService")
public class IRwPhotoInforServiceImpl implements IRwPhotoInfoService {

    @Resource
    private RwPhotoInfoMapper rwPhotoInfoMapper;

    @Override
    public RwPhotoInfo getPhotoInfoById(Long id) {

        return rwPhotoInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int savePhotoInfo(RwPhotoInfo photoInfo) {
        return rwPhotoInfoMapper.insertSelective(photoInfo);
    }

    @Override
    public Result<RwPhotoInfo> getPhotoInfoResultByQuery(Query<RwPhotoInfo> photoInfoQuery) {
        Result<RwPhotoInfo> result = new Result<>();
        result.setDataList(rwPhotoInfoMapper.selectResultByQuery(photoInfoQuery));
        result.setTotal(rwPhotoInfoMapper.countResultByQuery(photoInfoQuery));
        return result;
    }
}
