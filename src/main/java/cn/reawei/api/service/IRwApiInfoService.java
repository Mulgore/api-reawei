package cn.reawei.api.service;

import cn.reawei.api.model.RwApiInfo;
import org.springframework.stereotype.Service;

/**
 * Created by xingwu on 2017/5/26.
 */
@Service
public interface IRwApiInfoService {

    RwApiInfo getAppMemberById(Long id);

    int saveAppMember(RwApiInfo apiInfo);
}
