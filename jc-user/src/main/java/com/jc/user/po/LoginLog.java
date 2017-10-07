package com.jc.user.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "login_log")
public class LoginLog {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * 平台 1 android 2 Ios 3 pc 4 webapp
     */
    private Integer platform;

    private String ip;

    private String source;

    private String token;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取平台 1 android 2 Ios 3 pc 4 webapp
     *
     * @return platform - 平台 1 android 2 Ios 3 pc 4 webapp
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * 设置平台 1 android 2 Ios 3 pc 4 webapp
     *
     * @param platform 平台 1 android 2 Ios 3 pc 4 webapp
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}