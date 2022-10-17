package com.bovan.handler;

import com.bovan.domain.dto.SecurityUser;
import com.bovan.utils.JwtUtil;
import com.bovan.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.handler
 * @Author: bovan
 * @Date: 2022/10/13 15:46
 * @Description:
 */
@Component
public class TokenAuthenticationHandler extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
            System.out.println("userId:"+userId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:"+userId;
        SecurityUser user = redisCache.getCacheObject(redisKey);

        if(Objects.isNull(user)){
            throw new RuntimeException("用户未登录");
        }
        //放行
        filterChain.doFilter(request,response);
        // filterChain.doFilter();
    }
}
