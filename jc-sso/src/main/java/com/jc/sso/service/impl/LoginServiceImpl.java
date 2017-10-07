package com.jc.sso.service.impl;

import com.google.common.base.Preconditions;
import com.jc.base.dto.LoginLogDTO;
import com.jc.base.vo.LoginVO;
import com.jc.sso.po.LoginLog;
import com.jc.sso.service.LoginService;
import jodd.bean.BeanCopy;
import jodd.util.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Created by jasonzhu on 2017/9/28.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public LoginLogDTO login(LoginVO loginVO) {
        Preconditions.checkNotNull(loginVO,"参数不能为空");
        Preconditions.checkNotNull(loginVO.getUserName(),"用户名不能为空");
        Preconditions.checkNotNull(loginVO.getPassword(),"密码不能为空");
        String password = BCrypt.hashpw(loginVO.getPassword(),BCrypt.gensalt());
        LoginLog loginLog = new LoginLog();
        loginLog.setIp("1.1.1.1");
        loginLog.setToken("qwertyuiop");
        loginLog.setUserId(1);
        //类型转换
        LoginLogDTO logDTO =new LoginLogDTO();
        BeanCopy.beans(loginLog,logDTO).copy();
        return logDTO;
    }

}
