package com.jc.user.service.impl;

import com.jc.base.datasource.TargetDataSource;
import com.jc.base.dto.UserDTO;
import com.jc.base.vo.RegisterVO;
import com.jc.user.mapper.LoginLogMapper;
import com.jc.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by jasonzhu on 2017/9/28.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Override
    public UserDTO register(RegisterVO registerVO) {
        int n = loginLogMapper.selectCount(null);
        log.info("个数：{}",n);
        return null;
    }

    @TargetDataSource(name = "ds1")
    @Override
    public UserDTO registerSlave(RegisterVO registerVO) {
        int n = loginLogMapper.selectCount(null);
        log.info("个数：{}",n);
        return null;
    }
}
