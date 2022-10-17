package com.bovan.filter;

import com.bovan.common.lang.R;
import com.bovan.domain.dto.SecurityUser;
import com.bovan.domain.entity.User;
import com.bovan.utils.JwtUtil;
import com.bovan.utils.RedisCache;
import com.bovan.utils.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.filter
 * @Author: bovan
 * @Date: 2022/10/13 10:01
 * @Description:
 */
public class UsernamePasswordAuthenticationFilterImpl extends UsernamePasswordAuthenticationFilter{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    public UsernamePasswordAuthenticationFilterImpl(AuthenticationManager authenticationManager, RedisCache redisCache) {
        this.authenticationManager = authenticationManager;
        this.redisCache = redisCache;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login","POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User securityUser= new ObjectMapper().readValue(request.getInputStream(), User.class); //ObjectMapp.readValue能将输入流字符串换成对象
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(securityUser.getUsername(),securityUser.getPassword());
            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //获取用户信息
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        //根据用户名生成token
        String token = JwtUtil.createJWT(user.getUsername());
        //将用户信息存入redis
        String key = "login:"+ user.getUsername();
        redisCache.setCacheObject(key,user);
        //将封装的用户信息存入SecurityContextHolder中
        Authentication authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //返回token TODO 是否要返回内容给前端 ？？？
        ResponseUtils.out(response, R.ok().code(200).data("token",token));
        //放行
        chain.doFilter(request,response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtils.out(response, R.error().code(400).message("认证失败"));
    }
}
