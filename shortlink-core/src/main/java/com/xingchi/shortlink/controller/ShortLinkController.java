package com.xingchi.shortlink.controller;

import com.xingchi.common.unique.Result;
import com.xingchi.shortlink.common.model.dto.ShortLinkBuilderDTO;
import com.xingchi.shortlink.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链生成服务接口
 *
 * @author xingchi
 * @date 2023/4/17 23:50
 * @modified xingchi
 */
@RestController
@RequestMapping("/shortlink")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @Autowired
    public ShortLinkController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @PostMapping("/convert")
    public Result<String> convertLink(@RequestBody @Validated ShortLinkBuilderDTO shortLinkBuilderDTO) {
        return Result.ok(shortLinkService.buildShortLink(shortLinkBuilderDTO.getLongLink()));
    }

}
