package cn.reawei.api.common;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 致终于来到这里的勇敢的人：
 * <p>
 * 天将降大任于是人也，必先苦其心志，劳其筋骨，饿其体肤，空乏其身，行拂乱其所为，所以动心忍性，曾益其所不能。
 * <p>
 * 嗯！好了这个类的作用是: 缓存
 * <p>
 * Created by xingwu on 2017/6/8.
 */
//@Configuration
//@EnableCaching
public class CacheConfiguration {


    /**
     *  ehcache 主要的管理器
     * @param bean
     * @return
     */
//    @Bean
//    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
//        System.out.println("CacheConfiguration.ehCacheCacheManager()");
//        return new EhCacheCacheManager(bean.getObject());
//    }
//
//    /*
//       * 据shared与否的设置,
//       * Spring分别通过CacheManager.create()
//       * 或new CacheManager()方式来创建一个ehcache基地.
//       *
//       * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
//       *
//       */
//    @Bean
//    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
//        System.out.println("CacheConfiguration.ehCacheManagerFactoryBean()");
//        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
//        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("ehcache.xml"));
//        cacheManagerFactoryBean.setShared(true);
//        return cacheManagerFactoryBean;
//    }
}
