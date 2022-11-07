package com.bovan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bovan.domain.dto.SecurityUser;
import com.bovan.domain.entity.User;
import com.bovan.mapper.UserMapper;
import com.bovan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.service.impl
 * @Author: bovan
 * @Date: 2022/10/14 08:13
 * @Description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userName);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);

        if(Objects.isNull(user)){
            throw new RuntimeException("该用户不存在");
        }

        //TODO 查询对应的权限信息
//        List<String> list = new ArrayList<>(Arrays.asList("user"));
        List<String> list = userMapper.selectPermsByUserName(user.getUsername());

        //把数据封装成UserDetails返回

        return new SecurityUser(user,list);
    }
}
