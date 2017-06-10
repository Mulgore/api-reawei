package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwPhotoInfo;
import org.springframework.stereotype.Service;

/**
 * Created by xingwu on 2017/5/26.
 */
@Service
public interface IRwPhotoInfoService {

    /**
     * 根据id主键查询
     *
     * @param id
     * @return
     */
    RwPhotoInfo getPhotoInfoById(Long id);

    /**
     * 保存数据
     *
     * @param photoInfo
     * @return
     */
    int savePhotoInfo(RwPhotoInfo photoInfo);

    /**
     * 查询照片墙分页集合
     *
     * @param photoInfoQuery
     * @return
     */
    Result<RwPhotoInfo> getPhotoInfoResultByQuery(Query<RwPhotoInfo> photoInfoQuery);

    int updatePhotoInfo(RwPhotoInfo photoInfo);

    int removePhotoInfoById(Long id);
}
