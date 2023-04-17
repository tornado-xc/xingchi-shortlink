package com.xingchi.shortlink.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 短链接表
 *
 * @author xingchi
 * @date 2023/4/17 22:58
 * @modified xingchi
 */
@Data
@Entity
@Table(name = "link")
public class ShortLink {

    /**
     * id，使用雪花id生成
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 短链url
     */
    @Column(name = "short_url")
    private String shortUrl;

    /**
     * 长链url
     */
    @Column(name = "long_url")
    private String longUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 短链hash值
     */
    @Column(name = "short_hash")
    private String shortHash;

    /**
     * 长链hash值
     */
    @Column(name = "long_hash")
    private String longHash;

}
