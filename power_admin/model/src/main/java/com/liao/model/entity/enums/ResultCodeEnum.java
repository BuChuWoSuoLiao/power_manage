package com.liao.model.entity.enums;

/**
 * @BelongsPackage: com.liao.enums
 * @Author: Liao YunYang
 * @Description: 返回状态码枚举类
 * @CreateTime: 2023-06-28  23:16
 * @Version: 1.0
 */
public enum ResultCodeEnum {

    CODE_200(200, "请求成功！"),
    CODE_404(404, "请求地址不存在！"),

    // 通用 ===》 状态码500
    CODE_501(501, "数据为空！"),
    CODE_500(500, "服务器返回错误，请联系管理员！"),

    // 数据库相关 ===》 状态码600
    CODE_602(602, "删除数据失败！"),
    CODE_601(601, "修改数据失败！"),
    CODE_600(600, "添加数据失败！"),

    // 用户相关 状态码---》 900
    CODE_904(904, "用户未登录！"),
    CODE_903(903, "用户名不存在！"),
    CODE_902(902, "用户名已存在！"),
    CODE_901(901, "用户名或密码错误！");

    private Integer code;

    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
