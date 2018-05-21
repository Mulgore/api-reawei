package cn.reawei.api.common;

import cn.reawei.common.constants.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器
 *
 * @author qigong
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public SsoInterceptor getInterceptor(){
        return new SsoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SsoInterceptor()).addPathPatterns("/**").excludePathPatterns(Constant.PATH_PERFIX, Constant.PATH);
        super.addInterceptors(registry);
    }
}