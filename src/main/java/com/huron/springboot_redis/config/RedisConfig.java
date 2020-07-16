package com.huron.springboot_redis.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
          @Override
            public Object generate(Object target, Method method, Object... params){
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append(target.getClass().getName());
              stringBuilder.append(method.getName());
              for(Object object:params){
                  stringBuilder.append(object.toString());
              }
              return stringBuilder.toString();
          }
        };
    }
}
