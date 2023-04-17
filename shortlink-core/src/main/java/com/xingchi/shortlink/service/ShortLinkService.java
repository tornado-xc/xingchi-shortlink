package com.xingchi.shortlink.service;

import com.xingchi.shortlink.model.ShortLink;

/**
 * 短链服务接口
 *
 * @author xingchi
 * @date 2023/4/17 23:08
 * @modified xingchi
 */
public interface ShortLinkService {

    ShortLink getById(Long id);

    /**
     * 获取短链信息
     *
     * @param shortHash         短链hash
     * @param shortUrl          短链url
     * @return                  短链信息
     */
    ShortLink queryShortLink(String shortHash, String shortUrl);

    /**
     * 构建短链信息
     *
     * @param longLink          长链信息
     * @return                  短链信息
     */
    String buildShortLink(String longLink);

}
