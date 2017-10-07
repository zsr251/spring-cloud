package com.jc.user.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 登录消息
 * Created by jasonzhu on 2017/9/29.
 */
public interface LoginMQ {
    String INPUT_LOGIN = "inputLogin";
//    String OUTPUT_LOGIN = "outputLogin";

    /**
     * 消息接收
     * @return
     */
    @Input(INPUT_LOGIN)
    SubscribableChannel inputLogin();

    /**
     * 消息发送
     * @return
     */
//    @Output(OUTPUT_LOGIN)
//    MessageChannel outputLogin();
}
