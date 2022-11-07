package com.bovan.service;

import com.bovan.common.lang.Result;
import com.bovan.domain.dto.RegisterDto;
import com.bovan.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bovan
 * @since 2022-10-13
 */
public interface IUserService extends IService<User> {
    public Result insertUser(RegisterDto registerDto);
}
