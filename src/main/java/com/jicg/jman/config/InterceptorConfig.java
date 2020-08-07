package com.jicg.jman.config;

import com.jicg.jman.config.interceptors.ResultInterceptor;
import com.jicg.jman.config.interceptors.LogInterceptor;
import com.jicg.jman.config.interceptors.args.UserMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author jicg on 2020/8/4
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
        registry.addInterceptor(new ResultInterceptor())
                .addPathPatterns("/**/*.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/", "classpath:/static/")
                .setCachePeriod(31556926);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserMethodArgumentResolver());
    }
//    @Autowired
//    private StringHttpMessageConverter stringHttpMessageConverter;
//    @Autowired
//    private MappingJackson2HttpMessageConverter httpMessageConverter;
//
//    /**
//     * 添加转换器
//     */
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        for (int i = 0; i < converters.size(); i++) {
//            if (converters.get(i) instanceof StringHttpMessageConverter) {
//                converters.set(i, stringHttpMessageConverter);
//            }
//            if (converters.get(i) instanceof MappingJackson2HttpMessageConverter) {
//                converters.set(i, httpMessageConverter);
//            }
//        }
//    }

//    @Override
//     void addInterceptors(InterceptorRegistry registry) {
////        super.addInterceptors(registry);
//        registry.addInterceptor(new LogInterceptor());
//        registry.addInterceptor(new CombInterceptor())
//                .addPathPatterns("/**/*.html");
//    }
}
