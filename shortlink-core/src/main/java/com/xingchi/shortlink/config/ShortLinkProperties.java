package com.xingchi.shortlink.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 短链生成服务配置，无需配置对象，只用导入配置即可
 *
 * @author xingchi
 * @date 2023/4/17 23:23
 * @modified xingchi
 */
@Data
@Component
@ConfigurationProperties(prefix = "xingchi.short.link")
public class ShortLinkProperties {

    private String domain;

}
