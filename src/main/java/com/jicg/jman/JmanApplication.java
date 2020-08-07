package com.jicg.jman;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.jicg.jman.orm.mapper")
@EnableSwagger2
@EnableCaching
public class JmanApplication {
    public static void main(String[] args) {
        SpringApplication.run(JmanApplication.class, args);
    }
}
