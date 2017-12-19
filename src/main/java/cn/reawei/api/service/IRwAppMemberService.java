package cn.reawei.api.service;

import cn.reawei.api.model.RwAppMember;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;
import org.springframework.stereotype.Service;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个接口的作用是: 对用户接口权限，CRUD操作
 * <p>
 * Created by xingwu on 2017/6/8.
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

    Result<RwAppMember> getAppMemberResultByQuery(Query<RwAppMember> memberQuery);
}
