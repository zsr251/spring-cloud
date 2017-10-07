package com.jc.base.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by jasonzhu on 2017/9/28.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String userName;
    private String password;
    private String realName;
    private Integer age;
}
