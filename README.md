## 1. 数据库设计
```sql
-- 短链表，包含短链，原始链接，以及用来提升查询效率的short_hash和long_hash
CREATE TABLE `link`
(
    `id`         bigint(20) unsigned NOT NULL COMMENT '雪花id',
    `short_code` varchar(50)      NOT NULL COMMENT '短链代码',
    `long_url`   text             NOT NULL COMMENT '长链接',
    `created_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `short_hash` char(10)         NOT NULL COMMENT '短链哈希码',
    `long_hash`  char(10)         NOT NULL COMMENT '长链接哈希码',
    PRIMARY KEY (`id`),
    UNIQUE KEY `short_code` (`short_code`),
    UNIQUE KEY `short_hash` (`short_hash`),
    UNIQUE KEY `long_hash` (`long_hash`),
    INDEX `short_and_hash` (`short_hash`, `short_code`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='短链接表';
```
## 2. 实现思路
* 提供API接口，接收长链参数
* 为长链创建一个对应的短链
* 计算长链与短链的Hash值
* 存储短链与长链的对应关系
* 将短链hash码与短链长链存储到redis中，方便查询，提高查询效率
