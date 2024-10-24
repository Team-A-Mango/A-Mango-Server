package com.mango.amango.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class RedisUtil {

    private final StringRedisTemplate template;

    public void setDataExpire(String key, String value, long expire) {
        ValueOperations<String, String> ops = template.opsForValue();
        Duration duration = Duration.ofSeconds(expire);
        ops.set(key, value, duration);
    }

    public String getDataExpire(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }

    public void deleteDataExpire(String key) {
        template.delete(key);
    }

    public boolean exists(String key) {
        return Boolean.TRUE.equals(template.hasKey(key));
    }
}
