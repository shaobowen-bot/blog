package com.bovan.mapper;

import com.bovan.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bovan
 * @since 2022-10-13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
