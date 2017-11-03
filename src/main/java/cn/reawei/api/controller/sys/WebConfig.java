package cn.reawei.api.controller.sys;

import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>
 * WEB 初始化相关配置
 * </p>
 *
 * @author 青苗
 * @since 2017-08-08
 */
//@ControllerAdvice
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // kisso 拦截器配置
        registry.addInterceptor(new BaseController()).addPathPatterns("/**");
//        registry.addInterceptor(new SSOSpringInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
    }
}