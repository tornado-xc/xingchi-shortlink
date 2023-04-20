package com.xingchi.shortlink.controller;

import com.xingchi.shortlink.config.ShortLinkProperties;
import com.xingchi.shortlink.model.ShortLink;
import com.xingchi.shortlink.service.ShortLinkService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.xingchi.shortlink.config.Constants.SLASH;

/**
 * 重定向接口
 *
 * @author xingchi
 * @date 2023/4/20 18:50
 * @modified xingchi
 */
@Controller
public class RedirectController {

    @Autowired
    private ShortLinkProperties shortLinkProperties;

    @Autowired
    private ShortLinkService shortLinkService;

    @GetMapping("/{code}")
    public RedirectView redirectToLongUrl(@PathVariable("code") String code, HttpServletRequest request, HttpServletResponse response) {

        String shortUrl = request.getRequestURL().toString();
        if (shortUrl.endsWith(SLASH)) {
            shortUrl = shortUrl.substring(0, shortUrl.length() - 1);
        }
        String shortHash = DigestUtils.md5Hex(shortUrl);
        ShortLink shortLink = shortLinkService.queryShortLink(shortHash, shortUrl);
        if (Objects.isNull(shortLink)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new RedirectView(shortLinkProperties.getDomain() + "404");
        }
        return new RedirectView(shortLink.getLongUrl());
    }

}
