package cn.reawei.api.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 拦截器
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SSOInterceptor getInterceptor(){
        return new SSOInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}