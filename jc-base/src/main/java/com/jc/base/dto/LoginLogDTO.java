package com.jc.base.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginLogDTO implements Serializable{
    private static final long serialVersionUID = -6267578982479087984L;
    private Integer id;

    private Integer userId;

    /**
     * 平台 1 android 2 Ios 3 pc 4 webapp
     */
    private Integer platform;

    private String ip;

    private String source;

    private String token;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}