package com.liao.power.exception;

import com.liao.exception.CommonException.CommonException;
import com.liao.exception.CommonException.DataNotFontException;
import com.liao.exception.SqlException.DeleteException;
import com.liao.exception.SqlException.InsertException;
import com.liao.exception.SqlException.SqlException;
import com.liao.exception.SqlException.UpdateException;
import com.liao.exception.UserException.*;
import com.liao.model.entity.enums.ResultCodeEnum;
import com.liao.utils.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsPackage: com.liao.exception
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-06-29  23:26
 * @Version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*用户异常捕获*/
    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public ResponseResult userException(Throwable e) {
        if (e instanceof UsernameDuplicatedException) {         // 用户名重复异常
            return ResponseResult.error(ResultCodeEnum.CODE_903.getCode(), e.getMessage());
        } else if (e instanceof PasswordWrongException) {       // 密码错误异常
            return ResponseResult.error(ResultCodeEnum.CODE_901.getCode(), e.getMessage());
        } else if (e instanceof UsernameNotFoundException) {    // 用户名不存在异常
            return ResponseResult.error(ResultCodeEnum.CODE_903.getCode(), e.getMessage());
        } else if (e instanceof UserNotLoginException) {        // 用户未登录
            return ResponseResult.error(ResultCodeEnum.CODE_904.getCode(), e.getMessage());
        }
        return ResponseResult.error(ResultCodeEnum.CODE_500.getCode(), ResultCodeEnum.CODE_500.getMsg());
    }

    /*通用异常捕获*/
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ResponseResult commonException(Throwable e) {
        if (e instanceof DataNotFontException) {         // 数据为空
            return ResponseResult.error(ResultCodeEnum.CODE_501.getCode(), e.getMessage());
        }
        return ResponseResult.error(ResultCodeEnum.CODE_500.getCode(), ResultCodeEnum.CODE_500.getMsg());
    }

    /*sql异常捕获*/
    @ExceptionHandler(value = SqlException.class)
    @ResponseBody
    public ResponseResult sqlException(Throwable e) {
        if (e instanceof InsertException) {         // 添加数据库异常
            return ResponseResult.error(ResultCodeEnum.CODE_903.getCode(), e.getMessage());
        } else if (e instanceof DeleteException) {       // 删除加数据库异常
            return ResponseResult.error(ResultCodeEnum.CODE_901.getCode(), e.getMessage());
        } else if (e instanceof UpdateException) {    // 修改加数据库异常
            return ResponseResult.error(ResultCodeEnum.CODE_903.getCode(), e.getMessage());
        }
        return ResponseResult.error(ResultCodeEnum.CODE_500.getCode(), ResultCodeEnum.CODE_500.getMsg());
    }

}
