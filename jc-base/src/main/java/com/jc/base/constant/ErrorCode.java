package com.jc.base.constant;

/**
 * 错误码
 * Created by jasonzhu on 2017/9/16.
 */
public enum ErrorCode {
    OTHER("000", "其他"),
    SUCCESS("100", "成功"),
    ERROR("200", "失败"),
    WARNING("300", "警告"),
    AUTH_INVALID("400", "登录失效"),
    NOT_PERMIT("500", "访问受限"),
    NOT_SUPPORT("600", "不支持"),
    NOT_PERMIT_COUNT_LIMIT("501","访问过于频繁"),
    PARAM_ERROR("201", "参数异常"),
    PASSWORD_OR_USERNAME_ERROR("401", "用户名或密码错误");

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取异常码
     * @param code 异常码
     * @return
     */
    public ErrorCode getEnumByCode(String code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode().equals(code))
                return errorCode;
        }
        return OTHER;
    }

    /**
     * 根据code获得异常信息
     * @param code 异常码
     * @return
     */
    public String getMessageByCode(String code){
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode().equals(code))
                return errorCode.getMessage();
        }
        return OTHER.getMessage();
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
