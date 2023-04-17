package com.xingchi.shortlink;

import com.xingchi.shortlink.service.ShortLinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试
 *
 * @author xingchi
 * @date 2023/4/17 23:32
 * @modified xingchi
 */
@SpringBootTest(classes = ShortLinkApplication.class)
public class ShortLinkApplicationTests {

    @Autowired
    private ShortLinkService shortLinkService;

    /**
     * 测试短链生成
     */
    @Test
    public void testShortLinkGenerate() {
        String shortLink = shortLinkService.buildShortLink("https://www.baidu.com");
        System.out.println("shortLink = " + shortLink);
        Assertions.assertNotNull(shortLink);
    }

}
