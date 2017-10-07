package com.jc.user.config;

import com.jc.base.util.RateLimitUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 配置
 * Created by jasonzhu 5.
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    /**
     * 流量限制器
     * @param redisTemplate
     * @return
     */
    @Bean("redisRateLimitUtil")
    public RateLimitUtil applyRateLimitUtil(RedisTemplate redisTemplate){
        return new RateLimitUtil(redisTemplate,"user",5, 1);
    }
}
