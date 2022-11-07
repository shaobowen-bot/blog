package com.bovan.service;

import com.bovan.common.lang.Result;
import com.bovan.domain.dto.LoginDto;
import com.bovan.domain.entity.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.service
 * @Author: bovan
 * @Date: 2022/10/14 16:00
 * @Description:
 */
public interface LoginService {

    public Result login(HttpServletResponse response, LoginDto loginDto);
}
