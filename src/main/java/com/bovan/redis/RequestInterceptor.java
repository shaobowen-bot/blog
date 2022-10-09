package com.bovan.redis;


import com.bovan.common.Interface.AccessLimit;
import com.bovan.common.lang.Result;
import com.bovan.util.IpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by LPB on 2022-05-01.
 */
@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
                if (null == accessLimit) {
                    return true;
                }
                int seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                String ip = IpUtils.getIpAddr(request);
                String servletPath = request.getServletPath();
                String key = ip + ":" + request.getContextPath() + ":" + servletPath;
                // 已经访问的次数
                String count = (String) redisTemplate.opsForValue().get(key);

                if (null != count) {
                    log.info("(ip限流请求次数) ip:{} 接口名:{} 访问次数:{}", ip, servletPath, count);
                }
                if (null == count || -1 == Integer.parseInt(count.trim())) {
                    redisTemplate.opsForValue().set(key, "1", seconds, TimeUnit.SECONDS);
                    return true;
                }
                if (Integer.parseInt(count.trim()) < maxCount) {
                    double stringValueDouble = redisTemplate.opsForValue().increment(key, 1);

                    System.out.println("通过increment(K key, double delta)方法以增量方式存储double值:" + stringValueDouble);
                    return true;
                }
                _response(response);
                return false;
            }
        } catch (Exception e) {
            log.error("(ip限流请求次数) 请求异常 ex:{}", e.getMessage());
        }
        return true;
    }

    /**
     * 拦截器异常响应
     *
     * @param response
     * @throws IOException
     */
    public void _response(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        // R.fail 是接口响应统一封装返回
        response.getWriter().println(objectMapper.writeValueAsString(Result.fail(400, "请求过于频繁请稍后再试", null)));
        return;
    }

}


