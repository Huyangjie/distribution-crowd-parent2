package cn.leon.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Leon
 * @create 2022-05-10 1:11
 * IntelliJ IDEA
 * cn.leon.test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Resource
    private RedisTemplate<Object,Object> template;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @org.junit.Test
    public void test(){
        template.opsForValue().set("t1", "v");

        stringRedisTemplate.opsForValue().set("s", "v");
    }
}
