package com.xingchi.shortlink.service.impl;

import com.xingchi.shortlink.common.utils.Base62Utils;
import com.xingchi.shortlink.config.Constants;
import com.xingchi.shortlink.config.ShortLinkProperties;
import com.xingchi.shortlink.dao.ShortLinkDao;
import com.xingchi.shortlink.model.ShortLink;
import com.xingchi.shortlink.service.ShortLinkService;
import com.xingchi.unique.client.UniqueCodeClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 短链服务接口
 *
 * @author xingchi
 * @date 2023/4/17 23:09
 * @modified xingchi
 */
@Service
public class ShortLinkServiceImpl implements ShortLinkService {

    /**
     * 短链持久层对象
     */
    private final ShortLinkDao shortLinkDao;

    /**
     * 唯一code生成客户端
     */
    private final UniqueCodeClient uniqueCodeClient;

    /**
     * 短链属性配置
     */
    private final ShortLinkProperties shortLinkProperties;

    @Autowired
    public ShortLinkServiceImpl(ShortLinkDao shortLinkDao, UniqueCodeClient uniqueCodeClient, ShortLinkProperties shortLinkProperties) {
        this.shortLinkDao = shortLinkDao;
        this.uniqueCodeClient = uniqueCodeClient;
        this.shortLinkProperties = shortLinkProperties;
    }

    @Override
    public ShortLink getById(Long id) {
        return shortLinkDao.getById(id);
    }

    @Override
    public ShortLink queryShortLink(String shortHash, String shortUrl) {
        return shortLinkDao.getShortLinkByShortHashAndShortUrl(shortHash, shortUrl);
    }

    @Override
    public ShortLink queryShortLink(String shortUrl) {
        String shortHash = DigestUtils.md5Hex(shortUrl);
        return this.queryShortLink(shortHash, shortUrl);
    }

    @Override
    public String buildShortLink(String longLink) {

        // 获取短链唯一码以及雪花id
        Long redisId = uniqueCodeClient.redisId();
        Long snowflakeId = uniqueCodeClient.snowflakeId();

        if (redisId == null) {
            throw new IllegalArgumentException("短链生成失败，请重试");
        }
        // 转换为md5格式
        String uniqueCode = Base62Utils.encode(redisId);

        // 构建短链
        String domain = shortLinkProperties.getDomain();
        String shortLink = domain + uniqueCode;

        // 计算哈希值
        String longHash = DigestUtils.md5Hex(longLink);
        String shortHash = DigestUtils.md5Hex(shortLink);

        // 构建待插入数据
        ShortLink link = new ShortLink();
        link.setId(snowflakeId);
        link.setShortUrl(shortLink);
        link.setShortHash(shortHash);
        link.setLongUrl(longLink);
        link.setLongHash(longHash);
        link.setCreateTime(LocalDateTime.now());

        // 执行插入，并返回短链
        ShortLink result = shortLinkDao.save(link);
        return result.getShortUrl();
    }


}
