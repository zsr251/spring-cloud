package com.jc.user.controller;

import com.jc.base.constant.ErrorCode;
import com.jc.base.constant.ResultModel;
import com.jc.base.controller.BaseController;
import com.jc.base.dto.LoginLogDTO;
import com.jc.base.dto.UserDTO;
import com.jc.base.vo.LoginVO;
import com.jc.base.vo.RegisterVO;
import com.jc.user.client.SSOClient;
import com.jc.user.mq.RegisterMQ;
import com.jc.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jodd.bean.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by jasonzhu on 2017/9/15.
 */
@Api(description = "用户基础接口")
@RestController
@RefreshScope
@Slf4j
public class IndexController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private SSOClient ssoClient;
    @Autowired
    @Qualifier(RegisterMQ.OUTPUT_REGISTER)
    private MessageChannel outputRegister;

    @ApiOperation(value = "用户注册", notes = "用户用户名 密码 姓名是必须输入的")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultModel register(@ApiParam("用户登录参数") @Valid @RequestBody RegisterVO registerVO){
        logger.info("注册：{}",registerVO);
        UserDTO userDTO = new UserDTO();
        BeanCopy.beans(registerVO,userDTO).copy();
        LoginVO loginVO = new LoginVO();
        BeanCopy.beans(registerVO,loginVO).copy();
        ResultModel<LoginLogDTO> logRest = ssoClient.login(loginVO);
        if (!ErrorCode.SUCCESS.getCode().equals(logRest.getCode())){
            logger.warn("登录失败：{}",logRest.getMsg());
        }
        logger.info("登录：{}",logRest);
        //发送消息 注册成功
        outputRegister.send(MessageBuilder.withPayload(userDTO).build());
        return buildSuccessResponse();
    }

    @GetMapping("/testDs")
    public ResultModel testDs(){
        log.info("主库");
        userService.register(null);
        log.info("从库");
        userService.registerSlave(null);
        return buildSuccessResponse();
    }
}
