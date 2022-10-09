package com.bovan.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bovan.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bowen
 * @since 2022-09-11
 */
@Mapper
@TableName("t_user")
public interface TUserMapper extends BaseMapper<TUser> {

}
