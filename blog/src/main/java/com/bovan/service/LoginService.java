package com.bovan.service;

import com.bovan.common.lang.R;
import com.bovan.domain.entity.User;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.service
 * @Author: bovan
 * @Date: 2022/10/14 16:00
 * @Description:
 */
public interface LoginService {

    public R login(User user);
}
