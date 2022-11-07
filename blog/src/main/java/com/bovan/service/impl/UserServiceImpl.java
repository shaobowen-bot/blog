package com.bovan.service.impl;

import com.bovan.common.lang.Result;
import com.bovan.domain.dto.RegisterDto;
import com.bovan.domain.entity.User;
import com.bovan.domain.entity.UserRoles;
import com.bovan.mapper.RolePermissionMapper;
import com.bovan.mapper.UserMapper;
import com.bovan.mapper.UserRolesMapper;
import com.bovan.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bovan
 * @since 2022-10-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Result insertUser(RegisterDto registerDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passWord = bCryptPasswordEncoder.encode(registerDto.getPassword());
        String id = UUID.randomUUID().toString();
        userMapper.insertBUsernameAndPassword(registerDto.getUsername(),passWord,id);
        String username = registerDto.getUsername();
        userRolesMapper.insertById(id,"2");
        //通过用户ID
//        String roleId;
//        try{
//            UserRoles userRoles = userRolesMapper.selectByRoleId(2);
//            roleId = String.valueOf(userRoles.getRoleId());
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("查询失败");
//        }
//        rolePermissionMapper.insertById(roleId,2);
        return Result.ok().code(200).message("注册成功");
    }
}
