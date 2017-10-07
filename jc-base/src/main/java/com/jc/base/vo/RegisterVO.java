package com.jc.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by jasonzhu on 2017/9/28.
 */
@Data
@ApiModel(description = "用户注册VO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterVO {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "姓名", required = true)
    @NotEmpty(message = "姓名不能为空")
    private String realName;
    @ApiModelProperty(value = "年龄", required = false)
    private Integer age;
}
