package com.liao.utils;

import com.liao.model.entity.enums.ResultCodeEnum;

import java.util.HashMap;

/**
 * @BelongsPackage: com.liao.utils
 * @Author: Liao YunYang
 * @Description: 统一返回信息类
 * @CreateTime: 2023-06-28  23:18
 * @Version: 1.0
 */
public class ResponseResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 初始化值，默认为成功
     */
    private ResponseResult() {
        put("code", ResultCodeEnum.CODE_200.getCode());
        put("msg", ResultCodeEnum.CODE_200.getMsg());
    }

    /**
     * 加入自定义的code和msg
     *
     * @param code 状态码
     * @param msg  状态信息
     * @return
     */
    public static ResponseResult error(int code, String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("code", code);
        responseResult.put("msg", msg);
        return responseResult;
    }

    /**
     * 加入自定义的code和msg
     *
     * @param code 状态码
     * @param msg  状态信息
     * @return
     */
    public static ResponseResult ok(int code, String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.put("code", code);
        responseResult.put("msg", msg);
        return responseResult;
    }

    /**
     * 返回默认信息
     *
     * @return
     */
    public static ResponseResult ok() {
        return new ResponseResult();
    }

    /**
     * 重载map的put方法
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return
     */
    public ResponseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
