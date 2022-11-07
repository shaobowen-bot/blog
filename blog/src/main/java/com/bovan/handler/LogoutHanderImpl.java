package com.bovan.handler;


import com.bovan.utils.JwtUtil;
import com.bovan.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.handler
 * @Author: bovan
 * @Date: 2022/10/15 08:28
 * @Description:
 */
public class LogoutHanderImpl implements LogoutHandler {
    @Autowired
    private RedisCache redisCache;

    public LogoutHanderImpl(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //获取token
        String token = request.getHeader("AccessToken");

        //解析token
        String userName;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userName = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //删除redis、securityConetxtHolder中的用户信息
        String key = "login:" + userName;
        redisCache.deleteObject(key);
        SecurityContextHolder.clearContext();

    }
}
