package com.bovan.common.Interface;

import java.lang.annotation.*;

/**
 * Created by LPB on 2022-05-01.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AccessLimit {

    /**
     * 指定时间 单位：秒
     *
     * @return
     */
    int seconds() default 10;

    /**
     * 指定时间内API请求次数
     *
     * @return
     */
    int maxCount() default 5;

}


