package com.google.redis.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 【雅诗蘭黛   熬夜不怕黑眼圈  yeah】
 * @Description <p>//TODO</p>
 * @Date 15:12  2020/10/11
 * @Version 1.0.dev
 **/
@Configuration
public class RedisConfig {

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.101.10:6379");
        return (Redisson)Redisson.create(config);
    }
}
