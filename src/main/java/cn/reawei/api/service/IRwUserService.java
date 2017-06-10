package cn.reawei.api.service;

import cn.reawei.api.model.RwUser;
import org.springframework.stereotype.Service;

/**
 * Created by xingwu on 2017/6/9.
 */
@Service
public interface IRwUserService {

    RwUser getUserInfoByLoginName(String loginName);
}
