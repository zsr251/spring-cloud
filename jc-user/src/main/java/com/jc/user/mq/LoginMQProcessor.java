package com.jc.user.mq;

import com.jc.base.dto.LoginLogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 登录事件监听
 * Created by jasonzhu on 2017/9/29.
 */
@Slf4j
@Component
public class LoginMQProcessor {

    @StreamListener(LoginMQ.INPUT_LOGIN)
    public void inputLogin(LoginLogDTO loginLogDTO){
        log.info("======监听到登录信息=====");
        log.info("具体信息:{}",loginLogDTO);
        log.info("======登录信息处理完成=====");
    }
}
