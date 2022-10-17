package com.bovan.utils;

import com.bovan.common.lang.R;
import com.bovan.common.lang.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.utils
 * @Author: bovan
 * @Date: 2022/10/13 15:40
 * @Description:
 */
public class ResponseUtils {
    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
