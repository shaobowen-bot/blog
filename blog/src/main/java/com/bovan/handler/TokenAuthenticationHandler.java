package com.bovan.handler;

import com.bovan.domain.dto.SecurityUser;
import com.bovan.utils.JwtUtil;
import com.bovan.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("AccessToken");
        if(!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String userName;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userName = claims.getSubject();
            System.out.println("userName:"+userName);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token已过期");
        }
        //从redis中获取用户信息
        String redisKey = "login:"+ userName;
        SecurityUser user = redisCache.getCacheObject(redisKey);
        System.out.println("========="+user);
        stringRedisTemplate.expire("token",JwtUtil.JWT_TTL, TimeUnit.valueOf("L"));

        if(Objects.isNull(user)){
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
//        TODO 获取全部信息封装到Authentication中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request,response);
        // filterChain.doFilter();
    }
}
