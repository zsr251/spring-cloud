package com.jc.base.controller;

import com.jc.base.constant.ErrorCode;
import com.jc.base.constant.ResultModel;
import com.jc.base.exception.SSOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 * Created by jasonzhu 4.
 */
public class BaseErrorController extends BaseController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 业务验证异常
     */
    @ExceptionHandler(value = SSOException.class)
    @ResponseBody
    public ResultModel authException(SSOException exception) {
        logger.warn("业务验证异常", exception);
        Map<String, Object> d = new HashMap();
        d.put("message", exception.getMessage());
        d.put("code", exception.getCode());
        return buildResponse(exception.getCode(), exception.getMessage());
    }

    /**
     * 未知异常或参数异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel exception(Exception exception) {
        logger.error("未预期异常", exception);
        if (exception.getClass() == BindException.class)
            return buildResponse(ErrorCode.PARAM_ERROR.getCode(), ((BindException) exception).getFieldError().getDefaultMessage());
        if (exception.getClass() == MethodArgumentNotValidException.class)
            return buildResponse(ErrorCode.PARAM_ERROR.getCode(), ((MethodArgumentNotValidException) exception).getBindingResult().getFieldError().getDefaultMessage());
        if (exception.getClass()==MissingServletRequestParameterException.class)
            return buildResponse(ErrorCode.PARAM_ERROR);
        if (exception.getClass() == HttpMessageNotReadableException.class)
            return buildResponse(ErrorCode.PARAM_ERROR.getCode(), "提交的参数异常，请检查后再提交");
        if (exception.getClass() == SQLException.class)
            return buildResponse(ErrorCode.PARAM_ERROR.getCode(), "提交的参数不合法，请检查后再提交");
        if (exception.getClass() == IllegalArgumentException.class)
            return buildErrorResponse(exception.getMessage());
        return buildErrorResponse("操作失败，请检查后再重试");
    }

}
