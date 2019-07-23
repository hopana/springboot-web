package com.hp.springboot.result;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeMsg {

    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg EMPTY_TOKEN = new CodeMsg(500101, "token为空");
    public static CodeMsg AUTH_FAIL = new CodeMsg(500102, "认证失败");
    public static CodeMsg SIGN_ERROR = new CodeMsg(500102, "签名不正确");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg INVALID_PARAMETERS = new CodeMsg(500103, "参数错误");

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

}
