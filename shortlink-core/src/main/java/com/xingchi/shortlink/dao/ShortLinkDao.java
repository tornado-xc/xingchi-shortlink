package com.xingchi.shortlink.dao;

import com.xingchi.shortlink.model.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 短链数据库操作类
 *
 * @author xingchi
 * @date 2023/4/17 23:04
 * @modified xingchi
 */
public interface ShortLinkDao extends JpaRepository<ShortLink, Long> {

    /**
     * 获取短链接信息，根据短链Hash与短链url
     *
     * @param shortHash         短链hash
     * @param shortUrl          短链url
     * @return                  短链信息
     */
    ShortLink getShortLinkByShortHashAndShortUrl(String shortHash, String shortUrl);

}
