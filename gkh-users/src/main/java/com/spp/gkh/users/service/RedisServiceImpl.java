package com.spp.gkh.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@SuppressWarnings("unchecked")
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private ValueOperations<String, Object> valueOperations;

    /*@Autowired
    public RedisService(@Qualifier("genericRestTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }*/

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void set(String key, Object value) {
        valueOperations.set(key, value);
    }

    @Override
    public <T> T get(String key) {
        return (T) valueOperations.get(key);
    }

    @Override
    public <T> T delete(String key) {
        return (T) valueOperations.getAndDelete(key);
    }

    //public void auditLog(String message) {
    //valueOperations.set("AuditMessage-" + LocalDate.now() + ": ", message);
    //}

}
