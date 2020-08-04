package com.jicg.jman.config;

import com.jicg.jman.config.interceptors.CombInterceptor;
import com.jicg.jman.config.interceptors.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author jicg on 2020/8/4
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new LogInterceptor());
        registry.addInterceptor(new CombInterceptor())
                .addPathPatterns("/**/*.html");
    }
}
