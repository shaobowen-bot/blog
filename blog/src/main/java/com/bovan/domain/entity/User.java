package com.bovan.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author bovan
 * @since 2022-10-13
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("password")
    private String password;

    @TableField("userId")
    private String userId;

    @TableField("avator")
    private String avator;

    @TableField("username")
    private String username;

    @TableField("email")
    private String email;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("status")
    private String status;


}
