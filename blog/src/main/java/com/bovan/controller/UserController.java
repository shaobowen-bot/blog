package com.bovan.controller;

import com.bovan.common.lang.Result;
import com.bovan.domain.dto.LoginDto;
import com.bovan.domain.dto.RegisterDto;
import com.bovan.domain.entity.User;
import com.bovan.mapper.UserMapper;
import com.bovan.service.IUserService;
import com.bovan.service.LoginService;
import com.bovan.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bovan
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IUserService userService;
    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('test')")
    public String hello(){
        return "hello,world";
    }


    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto){
        return userService.insertUser(registerDto);
    }
    /**
     * 登录
     * @param response
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpServletResponse response, @Validated @RequestBody LoginDto loginDto){
        String key = "code"+loginDto.getUsername();
        Object code = redisTemplate.opsForValue().get(key);
        if(!loginDto.getCode().equals(code)){
            return Result.error().code(400).message("验证码错误");
        }
        return loginService.login(response,loginDto);
    }

    /**
     获取验证码
     */
//    @AccessLimit(seconds = 10, maxCount = 3)
    @PostMapping("/getcode")
    public Result getCode(@Validated @RequestBody LoginDto loginDto){
        String code = String.valueOf((int)((Math.random()*9+1)*100000));
        String key = "code"+loginDto.getUsername();
        redisTemplate.opsForValue().set(key,code);
        return Result.ok().code(200).message("获取成功").data("code",code);
    }

    /**
     * 刷新token
     * @param principal
     * @param response
     * @return
     */

    @GetMapping("/api-token-refresh")
    @PreAuthorize("hasAnyAuthority('user')")
    public Result refresh_token(Principal principal, HttpServletResponse response){
        User user = userMapper.getByUserName(principal.getName());
        //生成jwt
        String token = JwtUtil.createJWT(user.getUsername(),JwtUtil.JWT_TTL);
        //refresh_token
        String refreshToken = JwtUtil.createJWT(user.getUsername(),JwtUtil.JWT_TTL*2);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("refreshToken", refreshToken);
        return Result.ok().code(200).data(map);
    }


}
