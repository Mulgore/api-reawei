package cn.reawei.api.service;

import cn.reawei.api.model.RwApiInfo;
import org.springframework.stereotype.Service;

/**
 * Created by xingwu on 2017/5/26.
 */
@Service
public interface IRwApiInfoService {

    /**
     * 根据id主键查询
     *
     * @param id
     * @return
     */
    RwApiInfo getAppInfoById(Long id);

    /**
     * 保存数据
     *
     * @param apiInfo
     * @return
     */
    int saveAppInfo(RwApiInfo apiInfo);
}
