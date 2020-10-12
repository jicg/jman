package com.jicg.jman.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author jicg on 2020/8/7
 */
@Component
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "app")
//@PropertySource(value = "classpath:config.property", encoding = "utf-8")
public class AppConfig {
    private String title = "jman";
    private String icon = " https://portrait.gitee.com/uploads/avatars/user/149/448289_jicg_1578924211.png!avatar100";
    private String indexPage = "page/welcome-1.html";
}
