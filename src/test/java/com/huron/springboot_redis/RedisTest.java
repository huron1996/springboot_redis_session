package com.huron.springboot_redis;


import com.huron.springboot_redis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception{
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
        System.out.println(stringRedisTemplate.opsForValue().get("aaa").toString());
    }

    @Test
    public void testObj() throws Exception{
        User user = new User(140L,"jack","lxl199618","1595153442@qq.com","liangliang","123");
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        operations.set("com.huron",user);
        operations.set("com.huron.f",user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);

        boolean exists = redisTemplate.hasKey("com.huron.f");
        if(exists)
            System.out.println("exists is true");
        else
            System.out.println("exists is false");
    }





}
