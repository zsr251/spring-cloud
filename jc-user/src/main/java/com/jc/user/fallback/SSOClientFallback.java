package com.jc.user.fallback;

import com.jc.base.constant.ResultModel;
import com.jc.base.vo.LoginVO;
import com.jc.user.client.SSOClient;
import org.springframework.stereotype.Component;

/**
 * Created by jasonzhu on 2017/9/17.
 */
@Component
public class SSOClientFallback implements SSOClient {
    @Override
    public ResultModel login(LoginVO loginVO) {

        return new ResultModel("500","未找到服务");
    }
}
