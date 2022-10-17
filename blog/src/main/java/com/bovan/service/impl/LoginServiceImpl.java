package com.bovan.service.impl;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.service.impl
 * @Author: bovan
 * @Date: 2022/10/14 15:53
 * @Description:
 */

import com.bovan.common.lang.R;
import com.bovan.domain.dto.SecurityUser;
import com.bovan.domain.entity.User;
import com.bovan.service.LoginService;
import com.bovan.utils.JwtUtil;
import com.bovan.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    public R login(User user) {

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //认证通过，使用userId生成jwt jwt存入ResponseResult返回
        SecurityUser loginUser = (SecurityUser) authenticate.getPrincipal();
        String userId = loginUser.getCurrentUserInfo().getUserId();
        String jwt = JwtUtil.createJWT(userId);
//        Map<String,String> map = new HashMap<>();
//        map.put("token",jwt);
        //把完整的用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        return R.ok().code(200).message("登录成功").data("token",jwt);
    }
}
