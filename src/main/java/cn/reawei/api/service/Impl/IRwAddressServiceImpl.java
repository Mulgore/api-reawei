package cn.reawei.api.service.Impl;

import cn.reawei.api.mapper.RwAddressMapper;
import cn.reawei.api.model.RwAddress;
import cn.reawei.api.service.IRwAddressService;
import cn.reawei.common.page.Query;
import cn.reawei.common.page.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwAddressService")
public class IRwAddressServiceImpl implements IRwAddressService {


    @Autowired
    private RwAddressMapper rwAddressMapper;

    @Override
    @Transactional
    public Result<RwAddress> getRwAddressResultByQuery(Query<RwAddress> addressQuery) {
        Result<RwAddress> result = new Result<>();
        result.setDataList(rwAddressMapper.selectResultByQuery(addressQuery));
        result.setTotal(rwAddressMapper.countResultByQuery(addressQuery));
        return result;
    }
}
