package com.bovan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bovan.entity.TUser;
import com.bovan.mapper.TUserMapper;
import com.bovan.service.TUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bowen
 * @since 2022-09-11
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

}
