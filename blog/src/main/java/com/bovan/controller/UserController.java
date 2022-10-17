package com.bovan.controller;

import com.bovan.common.lang.R;
import com.bovan.domain.entity.User;
import com.bovan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('test1')")
    public String hello(){
        return "hello,world";
    }


    @PostMapping("/login")
    public R login(@RequestBody User user){
        return loginService.login(user);
    }

}
