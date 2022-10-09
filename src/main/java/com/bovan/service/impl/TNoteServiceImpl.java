package com.bovan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bovan.entity.TNote;
import com.bovan.mapper.TNoteMapper;
import com.bovan.service.TNoteService;
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
public class TNoteServiceImpl extends ServiceImpl<TNoteMapper, TNote> implements TNoteService {

}
