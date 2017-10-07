package com.jc.user.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 注册消息
 * Created by jasonzhu on 2017/9/29.
 */
public interface RegisterMQ {
//    String INPUT_REGISTER = "inputRegister";
    String OUTPUT_REGISTER = "outputRegister";

    /**
     * 消息接收
     * @return
     */
//    @Input(INPUT_REGISTER)
//    SubscribableChannel inputRegister();

    /**
     * 消息发送
     * @return
     */
    @Output(OUTPUT_REGISTER)
    MessageChannel outputRegister();
}
