package com.jicg.jman.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jicg on 2020/4/17
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
//        Parameter token = new ParameterBuilder()
//                .name("token")
//                .description("123")
//                .required(true)
//                .order(1)
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .build();
//        List<Parameter> globalParams = new ArrayList<>(1);
//        globalParams.add(token);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.jicg.jman.web.controller.api"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build().apiInfo(apinfo());
//                .globalOperationParameters(globalParams);
    }

    private ApiInfo apinfo() {
        return new ApiInfoBuilder()
                .title("小鸡后台管理")
                .description("Api文档")
                .version("0.1")
//                .contact(
//                        new Contact("jicg",
//                                "http://github.com/jicg",
//                                "jicg@hellowcloud.com"
//                        )
//                )
//                        .license("The Apache License")
//                        .licenseUrl("http://www.hellowcloud.com")
                .build();
    }
}
