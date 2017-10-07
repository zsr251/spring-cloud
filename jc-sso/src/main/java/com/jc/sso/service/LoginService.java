package com.jc.sso.service;


import com.jc.base.dto.LoginLogDTO;
import com.jc.base.vo.LoginVO;

/**
 * 登录相关
 * Created by jasonzhu on 2017/9/15.
 */
public interface LoginService {
    public LoginLogDTO login(LoginVO loginVO);
}
