package cn.reawei.api.mapper;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.model.RwAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
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