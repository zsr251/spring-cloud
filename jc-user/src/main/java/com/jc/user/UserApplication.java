package com.jc.user;

import com.jc.base.datasource.DynamicDataSourceRegister;
import com.jc.user.mq.LoginMQ;
import com.jc.user.mq.RegisterMQ;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Import;

/**
 * Created by jasonzhu on 2017/9/17.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableBinding({RegisterMQ.class, LoginMQ.class})
@Import(DynamicDataSourceRegister.class)
@MapperScan(basePackages = {"com.jc.user.mapper"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
