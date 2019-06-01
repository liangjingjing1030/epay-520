package org.epay.mgr.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 调用web服务路径配置
 * GaoLiang
 * 2019年5月14日09:23:37
 */
@Component
@Configuration
@PropertySource(name = "props", value = "classpath:application.properties", ignoreResourceNotFound = false)
@ConfigurationProperties(prefix = "web")
public class CommonConfig {

    // web服务调用路径
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
