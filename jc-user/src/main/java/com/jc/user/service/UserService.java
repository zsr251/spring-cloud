package com.jc.user.service;

import com.jc.base.dto.UserDTO;
import com.jc.base.vo.RegisterVO;

/**
 * Created by jasonzhu on 2017/9/28.
 */
public interface UserService {
    public UserDTO register(RegisterVO registerVO);
    public UserDTO registerSlave(RegisterVO registerVO);
}
