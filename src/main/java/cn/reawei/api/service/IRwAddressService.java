package cn.reawei.api.service;

import cn.reawei.api.common.utils.Page.Query;
import cn.reawei.api.common.utils.Page.Result;
import cn.reawei.api.model.RwAddress;
import org.springframework.stereotype.Service;


/**
 * Created by xingwu on 2017/5/24.
 */
@Service
public interface IRwAddressService {

    Result<RwAddress> getRwAddressResultByQuery(Query<RwAddress> addressQuery);
}
