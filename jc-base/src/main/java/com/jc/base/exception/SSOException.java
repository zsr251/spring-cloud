package com.jc.base.exception;

import com.jc.base.constant.ErrorCode;

/**
 * 登录异常
 * Created by jasonzhu on 2017/9/15.
 */
public class SSOException extends RuntimeException {
    private String code;
    public SSOException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
    public SSOException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
