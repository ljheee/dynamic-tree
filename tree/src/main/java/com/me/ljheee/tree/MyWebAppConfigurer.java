package com.me.ljheee.tree;

import com.me.ljheee.tree.interceptor.ParamsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截

        //只有经过DispatcherServlet 的请求，才会走拦截器链
        registry.addInterceptor(new ParamsInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
