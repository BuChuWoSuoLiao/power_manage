package com.liao.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsPackage: com.liao.utils
 * @Author: Liao YunYang
 * @Description: Response返回前端
 * @CreateTime: 2023-06-28  23:18
 * @Version: 1.0
 */
public class ResponseUtil {

    public static void out(HttpServletResponse response, ResponseResult r) {
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