package com.xingchi.shortlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 短链服务启动类
 *
 * @author xingchi
 * @date 2023/4/17 22:37
 * @modified xingchi
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.xingchi"})
@EnableFeignClients("com.xingchi")
public class ShortLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApplication.class, args);
    }

}
