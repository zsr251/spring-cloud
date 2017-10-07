package com.jc.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by jasonzhu .
 */
@Data
@ApiModel(description = "用户登录VO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginVO {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "登录ip")
    private String ip;
    @ApiModelProperty(value = "平台")
    private Integer platform;
    @ApiModelProperty(value = "来源")
    private String source;

}
