package cn.reawei.api.service.Impl;

import cn.reawei.api.service.IRwCacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by xingwu on 2017/5/24.
 */
@Service("rwCacheService")
@EnableCaching
public class IRwCacheServiceImpl implements IRwCacheService {


    @Cacheable(value = "concurrenmapcache")
    public long getByCache() {
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Timestamp(System.currentTimeMillis()).getTime();
    }

}
