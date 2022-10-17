package com.bovan.utils;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.utils
 * @Author: bovan
 * @Date: 2022/10/14 11:41
 * @Description:
 */
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class WebUtils {

    public static String renderString(HttpServletResponse response, String str){
        try{
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
