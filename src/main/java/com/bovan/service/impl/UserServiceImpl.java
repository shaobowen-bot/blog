package com.bovan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bovan.entity.User;
import com.bovan.mapper.UserMapper;
import com.bovan.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bovan
 * @since 2022-09-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
