package com.ms.principal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    public void save(int times) {
        redisTemplate.opsForValue().set("timesCalled", times);
    }

    public Integer getTimesCalled() {
        return redisTemplate.opsForValue().get("timesCalled");
    }

}
