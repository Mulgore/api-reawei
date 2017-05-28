package cn.reawei.api.service;

import cn.reawei.api.model.RwAppMember;
import org.springframework.stereotype.Service;

/**
 * Created by xingwu on 2017/5/26.
 */
@Service
public interface IRwAppMemberService {

    /**
     * 根据id主键查询Appmember
     *
     * @param id
     * @return
     */
    RwAppMember getAppMemberById(Long id);

    /**
     * 保存appMember数据
     *
     * @param appMember
     * @return
     */
    int saveAppMember(RwAppMember appMember);

    /**
     * 根据ID主键修改数据
     *
     * @param appMember appMember数据
     * @return
     */
    int updateAppMemberById(RwAppMember appMember);

    /**
     * 根据主键ID删除数据(物理删除)
     *
     * @param id 主键ID
     * @return
     */
    int deleteAppMemberById(Long id);
}
