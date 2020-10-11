package com.google.redis.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author 【雅诗蘭黛   熬夜不怕黑眼圈  yeah】
 * @Description <p>//TODO</p>
 * @Date 14:22  2020/10/11
 * @Version 1.0.dev
 **/
@RestController
public class RedisController {

    private final String Key = "LOCK";

    @Autowired
    private Redisson redisson;

    @Autowired
    StringRedisTemplate template;

    public String lock(){
        String id = UUID.randomUUID().toString();
       RLock rLock =  redisson.getLock(Key);
        try{
            boolean flag = template.opsForValue().setIfAbsent(Key,id,10, TimeUnit.SECONDS);
            if(!flag){
                return "error";
            }
            rLock.lock();
            //业务逻辑
        }finally {
            if(id .equals(template.opsForValue().get(Key))){
                template.delete(Key);
            }
            rLock.unlock();

        }
        return null;
    }
}
