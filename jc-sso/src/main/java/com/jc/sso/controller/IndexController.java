package com.jc.sso.controller;

import com.jc.base.constant.ResultModel;
import com.jc.base.controller.BaseController;
import com.jc.base.dto.LoginLogDTO;
import com.jc.base.vo.LoginVO;
import com.jc.sso.mq.LoginMQ;
import com.jc.sso.po.LoginLog;
import com.jc.sso.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by jasonzhu on 2017/9/15.
 */
@Api(description = "基础接口")
@RestController
@RefreshScope
@Slf4j
public class IndexController extends BaseController {
@Autowired
private LoginService loginService;
    @Autowired
    @Qualifier(LoginMQ.OUTPUT_LOGIN)
    private MessageChannel outputLogin;

    @ApiOperation(value = "用户登录", notes = "用户登录，用户名 密码是必须输入的")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel login(@ApiParam("用户登录参数") @Valid @RequestBody LoginVO loginVO){
        LoginLogDTO loginLog = loginService.login(loginVO);
        log.info("登录完成:{}",loginLog);
        //发送MQ
        outputLogin.send(MessageBuilder.withPayload(loginLog).build());
        return buildSuccessResponse(loginLog);
    }

}
