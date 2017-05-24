package cn.reawei.api.mapper;


import cn.reawei.api.model.RwAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
  * 操作日志表 Mapper 接口
 * </p>
 *
 * @author xingwu
 * @since 2017-03-28
 */
@Mapper
public interface RwAddressMapper {

    List<RwAddress> getAddressList();
}