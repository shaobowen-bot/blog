package com.bovan.shiro;

import lombok.Data;

import java.io.Serializable;

/*
 *登录成功后返回一个用户信息的载体
 * 封装用户的主要信息，用于放置到token中返回给客户端
 */

@Data
public class AccountProfile implements Serializable {

    private Long id;
    private String username;
    private String avatar;
    private String email;

}

