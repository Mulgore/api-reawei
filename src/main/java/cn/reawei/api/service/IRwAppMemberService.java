package cn.reawei.api.service;

import cn.reawei.api.model.RwAppMember;
import org.springframework.stereotype.Service;

/**
 * Created by xingwu on 2017/5/26.
 */
@Service
public interface IRwAppMemberService {

    RwAppMember getAppMemberById(Long id);

    int saveAppMember(RwAppMember appMember);

    int updateAppMemberById(RwAppMember appMember);
}
