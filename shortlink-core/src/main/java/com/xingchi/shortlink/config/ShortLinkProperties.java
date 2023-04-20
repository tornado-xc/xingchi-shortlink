package com.xingchi.shortlink.config;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static com.xingchi.shortlink.config.Constants.REGEX_URL;

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
public class ShortLinkProperties implements InitializingBean {

    private String domain;

    public String getDomain() {
        if (!domain.endsWith(Constants.SLASH)) {
            domain += Constants.SLASH;
        }
        return domain;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (!StringUtils.hasText(domain)) {
            throw new IllegalArgumentException("请配置短链域名");
        }

        if (!domain.matches(REGEX_URL)) {
            throw new IllegalArgumentException("请配置正确的url地址");
        }
    }
}
