package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwPhotoInfo;
import org.springframework.stereotype.Service;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个接口的作用是: 对照片，CRUD操作
 * <p>
 * Created by xingwu on 2017/6/8.
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
