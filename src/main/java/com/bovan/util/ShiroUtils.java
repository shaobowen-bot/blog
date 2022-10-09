package com.bovan.util;

import com.bovan.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {
    /**
     * 获取当前用户
     *
     * @return
     */
    public static AccountProfile getProfile() {
        System.out.println("进入工具类");
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        System.out.println(accountProfile);
        System.out.println("user.toString()======================");
        return accountProfile;
    }
}
