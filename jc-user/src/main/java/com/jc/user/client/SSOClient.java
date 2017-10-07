package com.jc.user.client;

import com.jc.base.constant.ResultModel;
import com.jc.base.dto.LoginLogDTO;
import com.jc.base.vo.LoginVO;
import com.jc.user.fallback.SSOClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by jasonzhu on 2017/9/17.
 */
@FeignClient(name = "cloud-sso", fallback = SSOClientFallback.class)
public interface SSOClient {
    @PostMapping(value = "/login")
    public ResultModel<LoginLogDTO> login(LoginVO loginVO);
}
