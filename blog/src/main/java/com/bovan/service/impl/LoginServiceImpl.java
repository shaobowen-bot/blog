package com.bovan.service.impl;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.service.impl
 * @Author: bovan
 * @Date: 2022/10/14 15:53
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bovan.common.exception.GlobalException;
import com.bovan.common.lang.Result;
import com.bovan.domain.dto.LoginDto;
import com.bovan.domain.dto.SecurityUser;
import com.bovan.domain.entity.User;
import com.bovan.service.LoginService;
import com.bovan.utils.JwtUtil;
import com.bovan.utils.RedisCache;

import cn.hutool.core.map.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @BelongsProject: IDEA1
 * @BelongsPackage: com.bovan.service
 * @Author: bovan
 * @Date: 2022/10/5 16:27
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Result login(HttpServletResponse response, LoginDto loginDto) {

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        //认证通过，使用userId生成jwt jwt存入ResponseResult返回
        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();
        String userName = securityUser.getCurrentUserInfo().getUsername();
        //生成access_token
        String accessToken = JwtUtil.createJWT(userName);
        //生成refreshToken
        String refreshToken = JwtUtil.createJWT(userName);

        redisCache.setCacheObject(accessToken,userName);
        redisCache.setCacheObject(refreshToken,userName);

        //返回前端
        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("permission",securityUser.getPermissionValueList());
        userInfo.put("username",securityUser.getUsername());



        //把完整的用户信息存入redis
        redisCache.setCacheObject("login:" + userName, securityUser);

        Cookie cookie = new Cookie("user", "user.getUsername()");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);

        //响应体
        response.setHeader("AccessToken",accessToken);
        response.setHeader("RefreshToken",refreshToken);
        response.setHeader("Access-Control-Expose-Headers","AccessToken");


        return Result.ok().code(200).message("登录成功")
                .data("userInfo",userInfo);
    }
}
