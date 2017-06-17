package cn.reawei.api.service.Impl;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.mapper.RwAddressMapper;
import cn.reawei.api.model.RwAddress;
import cn.reawei.api.service.IRwAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwAddressService")
public class IRwAddressServiceImpl implements IRwAddressService {


    @Autowired
    private RwAddressMapper rwAddressMapper;

    @Override
    public Result<RwAddress> getRwAddressResultByQuery(Query<RwAddress> addressQuery) {
        Result<RwAddress> result = new Result<>();
        result.setDataList(rwAddressMapper.selectResultByQuery(addressQuery));
        result.setTotal(rwAddressMapper.countResultByQuery(addressQuery));
        return result;
    }
}
