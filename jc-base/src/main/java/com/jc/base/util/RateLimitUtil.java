package com.jc.base.util;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.RateLimiter;
import com.jc.base.constant.RedisKey;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 限流工具
 * Created by jasonzhu 6.
 */
public class RateLimitUtil {
    private RedisTemplate redisTemplate;
    private String key;
    private int acceptCount;
    private long second;
    private RateLimiter rateLimiter;
    private long timeoutSecond;

    /**
     * 初始化分布式频次限制器 暂无等待超时
     *
     * @param redisTemplate
     * @param key           限制器key
     * @param acceptCount   单位时间内允许的请求个数
     * @param second        单位时间长度 秒
     */
    public RateLimitUtil(RedisTemplate redisTemplate, String key, int acceptCount, long second) {
        Preconditions.checkNotNull(redisTemplate, "redisTemplate不能为空");
        this.redisTemplate = redisTemplate;
        this.key = StringUtils.isEmpty(key) ? RedisKey.LIMIT_ + generateKey() : RedisKey.LIMIT_ + key;
        this.acceptCount = acceptCount < 1 ? 1 : acceptCount;
        this.second = second < 1 ? 1 : second;
    }

    /**
     * 初始化单机版批次限制器
     *
     * @param acceptCount   单位时间内允许的请求个数
     * @param timeoutSecond 超时时间
     */
    public RateLimitUtil(int acceptCount, long timeoutSecond) {
        rateLimiter = RateLimiter.create(acceptCount < 1 ? 1 : acceptCount);
        this.timeoutSecond = timeoutSecond;
    }

    /**
     * 尝试检测是否被允许
     *
     * @return
     */
    public boolean tryAcquire() {
        if (rateLimiter != null) {
            return rateLimiter.tryAcquire(timeoutSecond, TimeUnit.SECONDS);
        }
        return tryAcquireRedis();
    }

    /**
     * 通过redis 分布式 无超时时间
     *
     * @return
     */
    private boolean tryAcquireRedis() {
        if (redisTemplate == null) return true;
        long i = redisTemplate.opsForValue().increment(key, 1);
        if (i == 1L) {
            redisTemplate.expire(key, second, TimeUnit.SECONDS);
        }
        if (i > acceptCount) {
            return false;
        }
        return true;
    }

    /**
     * 生成唯一key
     */
    private static String generateKey() {
        return new StringBuilder(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date())).append(UUID.randomUUID().toString().substring(0, 3)).toString();
    }
}
