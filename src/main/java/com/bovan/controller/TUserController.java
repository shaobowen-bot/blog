package com.bovan.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bovan.common.Interface.AccessLimit;
import com.bovan.common.dto.LoginDto;
import com.bovan.common.dto.RegisterDto;
import com.bovan.common.lang.Result;
import com.bovan.entity.TUser;
import com.bovan.mapper.TUserMapper;
import com.bovan.service.TUserService;
import com.bovan.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
public class TUserController {

    @Autowired
    TUserService tUserService;

    @Autowired
    JwtUtils jwtUtils;
    //    BlogMapper blogMapper;
    @Autowired
    TUserMapper tUserMapper;

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 获取验证码
     */
    @AccessLimit(seconds = 10, maxCount = 3)
    @PostMapping("/getcode")
    public Result getCode(@Validated @RequestBody LoginDto loginDto) {


        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        String key = "code" + loginDto.getUsername();
        redisTemplate.opsForValue().set(key, code);
        return Result.success(200, "获取成功", code);
    }

    /**
     * 登录逻辑
     */
    @PostMapping("/login")
    public Result<TUser> login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        TUser user = tUserService.getOne(new QueryWrapper<TUser>().eq("username", loginDto.getUsername()));
        System.out.println(user.getUsername());
        Assert.notNull(user, "用户不存在");


        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail(400, "密码不正确", null);
        }
        String key = "code" + loginDto.getUsername();
        Object code = redisTemplate.opsForValue().get(key);
        if (!loginDto.getCode().equals(code)) {
            return Result.fail(400, "验证码不正确", null);
        }
        String jwt = jwtUtils.generateToken(user.getId());  //生成一个token

        //将用户信息保存在session中
//        request.getSession().setAttribute("user",user);
//        request.getSession().setAttribute("user",user.getUsername());
        Cookie cookie = new Cookie("user", "user.getUsername()");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);

        //响应体
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        //hutool快速创建map
        return Result.success(
                200, "登录成功", MapUtil.builder()
                        .put("id", user.getId())
                        .put("username", user.getUsername())
                        .put("avatar", user.getAvator())
                        .put("email", user.getEmail())
                        .map());
    }

    /**
     * 注册逻辑
     */
    @PostMapping("/register")
    public Result<TUser> register(@RequestBody RegisterDto registerDto) {
        TUser user = tUserService.getOne(new QueryWrapper<TUser>().eq("username", registerDto.getUsername()));

        if (user == null) {
            TUser entity = new TUser();
            entity.setUsername(registerDto.getUsername());
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            entity.setPassword(SecureUtil.md5(registerDto.getPassword()));
//            entity.setStatus(String.valueOf(0));
            int result = tUserMapper.insert(entity);
            System.out.println(result);
            return Result.success(200, "注册成功", null);
        }
        return Result.fail(400, "注册失败", null);
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();    //获取当前当前登录用户的信息
        return Result.success(200, "退出成功", null);
    }


}
