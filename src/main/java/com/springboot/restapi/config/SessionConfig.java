package com.springboot.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class SessionConfig {

        @Bean
        public JedisConnectionFactory connectionFactory() {
        	RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("redis", 6379);
            return new JedisConnectionFactory(config);
        }
}