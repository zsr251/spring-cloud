package com.jc.sso.mq;

import com.jc.base.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 注册事件监听
 * Created by jasonzhu on 2017/9/29.
 */
@Slf4j
@Component
public class RegisterMQProcessor {
    @StreamListener(RegisterMQ.INPUT_REGISTER)
    public void inputRegister(UserDTO userDTO){
        log.info("======监听到注册信息=====");
        log.info("具体信息:{}",userDTO);
        log.info("======登录信息处理完成=====");
    }
}
