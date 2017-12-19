package cn.reawei.api.mapper;

import cn.reawei.api.model.RwAddress;
import cn.reawei.common.page.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个Mapper的作用是: 访问数据库查询地址信息
 */
@Repository
public interface RwAddressMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RwAddress record);

    int insertSelective(RwAddress record);

    RwAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RwAddress record);

    int updateByPrimaryKey(RwAddress record);

    List<RwAddress> selectResultByQuery(Query<RwAddress> addressQuery);

    int countResultByQuery(Query<RwAddress> addressQuery);
}