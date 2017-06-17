package cn.reawei.api.mapper;

import cn.reawei.api.model.RwUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个类的作用是: 访问数据库查询用户信息
 */
public interface RwUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RwUser record);

    int insertSelective(RwUser record);

    RwUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwUser record);

    int updateByPrimaryKey(RwUser record);

    RwUser selectByLoginName(String loginName);
}