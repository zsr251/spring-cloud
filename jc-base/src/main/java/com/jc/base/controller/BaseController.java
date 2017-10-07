package com.jc.base.controller;

import com.jc.base.constant.ErrorCode;
import com.jc.base.constant.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 基础Controller
 */
public class BaseController {
    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected <T> ResultModel<T> buildResponse(ErrorCode errorCode) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(errorCode.getCode());
        model.setMsg(errorCode.getMessage());
        return model;
    }

    protected <T> ResultModel<T> buildResponse(ErrorCode errorCode, T obj) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(errorCode.getCode());
        model.setMsg(errorCode.getMessage());
        model.setData(obj);
        return model;
    }

    protected <T> ResultModel<T> buildResponse(String code, String msg) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(code);
        model.setMsg(msg);
        return model;
    }

    protected <T> ResultModel<T> buildResponse(String code, String msg, T obj) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(code);
        model.setMsg(msg);
        model.setData(obj);
        return model;
    }


    protected <T> ResultModel<T> buildErrorResponse(String msg) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(ErrorCode.ERROR.getCode());
        model.setMsg(msg);
        return model;
    }

    protected <T> ResultModel<T> buildErrorResponse(String msg, T obj) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(ErrorCode.ERROR.getCode());
        model.setMsg(msg);
        model.setData(obj);
        return model;
    }

    protected <T> ResultModel<T> buildNotPermitResponse() {
        return buildNotPermitResponse(ErrorCode.NOT_PERMIT.getMessage());
    }

    protected <T> ResultModel<T> buildNotPermitResponse(String msg) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(ErrorCode.NOT_PERMIT.getCode());
        model.setMsg(msg);
        return model;
    }

    protected <T> ResultModel<T> buildLimitResponse() {
        return buildLimitResponse(ErrorCode.NOT_PERMIT_COUNT_LIMIT.getMessage());
    }

    protected <T> ResultModel<T> buildLimitResponse(String msg) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(ErrorCode.NOT_PERMIT_COUNT_LIMIT.getCode());
        model.setMsg(msg);
        return model;
    }

    protected <T> ResultModel<T> buildAuthInvalidResponse(String msg) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(ErrorCode.AUTH_INVALID.getCode());
        model.setMsg(msg);
        return model;
    }

    protected <T> ResultModel<T> buildSuccessResponse() {
        return buildSuccessResponse(null);
    }

    protected <T> ResultModel<T> buildSuccessResponse(T obj) {
        ResultModel<T> model = new ResultModel<T>();
        model.setCode(ErrorCode.SUCCESS.getCode());
        model.setMsg(ErrorCode.SUCCESS.getMessage());
        model.setData(obj);
        return model;
    }

    private <T> Map<String, Object> parseDTO(T obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj != null) {
            Set<Field> fset = new HashSet<>();
            fset.addAll(getAllFields(obj.getClass()));
            for (Field f : fset) {
                f.setAccessible(true);
                if (f.getType() == BigDecimal.class) {
                    try {
                        map.put(f.getName(), f.get(obj) != null ? String.valueOf((f.get(obj))) : null);
                    } catch (Exception e) {
                        logger.error(f.getName() + "解析失败");
                    }
                } else if (f.getType().getName().endsWith("DTO")) {
                    try {
                        map.put(f.getName(), parseDTO(f.get(obj)));
                    } catch (Exception e) {
                        logger.error(f.getName() + "解析失败");
                    }
                } else {
                    try {
                        map.put(f.getName(), f.get(obj));
                    } catch (Exception e) {
                        logger.error(f.getName() + "解析失败");
                    }
                }
            }
        }

        return map;
    }


    private Set<Field> getAllFields(Class t) {
        Set<Field> fset = new HashSet<>();
        Field[] deClaredFields = t.getDeclaredFields();
        fset.addAll(Arrays.asList(deClaredFields));
        Class c = t.getSuperclass();
        if (c != null)
            fset.addAll(getAllFields(c));
        return fset;
    }


//    public User getUser () throws AuthException {
//        // 校验登录状态
//        User user = Constants.userThreadLocal.get();
//        if (user == null) {
//            throw new AuthException("未登录",AuthException.NOT_LOGIN);
//        }
//        return user;
//    }
//
//    public User getUserByToken(String token) throws AuthException{
//		if (StringUtils.isEmpty(token)){
//			throw new AuthException("未登录",AuthException.NOT_LOGIN);
//		}
//		User u = authService.validateToken(token);
//		if (u==null){
//			throw new AuthException("未登录",AuthException.NOT_LOGIN);
//		}
//		return u;
//	}

    protected boolean isMoblieClient(HttpServletRequest request) {
        boolean flag = false;
        String agent = request.getHeader("user-agent");
        String[] keywords = {"Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"};
        if (!agent.contains("Windows NT") ||
                (agent.contains("Windows NT") && agent.contains("compatible; MSIE 9.0;"))) {
            if (!agent.contains("Windows NT") && !agent.contains("Macintosh")) {
                for (String item : keywords) {
                    if (agent.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 获得真实ip
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getRemoteHost();
        String realIP = request.getHeader("X-Real-IP");
        if (isNotBlank(realIP)) {
            ip = realIP;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
        if (ip != null && ip.length() > 15) {
            int index = ip.indexOf(",");
            if (index > -1) {
                ip = ip.substring(0, index);
            }
        }
        return ip;
    }

    /**
     * 通用框架校验异常处理
     *
     * @param res
     * @return
     */
    ResultModel commonValidate(BindingResult res) {
        if (res.getErrorCount() > 0) {
            StringBuilder errorMsg = new StringBuilder();
            res.getFieldErrors().forEach(error -> errorMsg.append("[").append(error.getDefaultMessage()).append("]"));
            return buildErrorResponse(errorMsg.toString());
        }
        return null;
    }

}
