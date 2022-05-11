package cn.leon.service;

import cn.leon.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Leon
 * @create 2022-05-11 1:23
 * IntelliJ IDEA
 * cn.leon.service
 */
@FeignClient(value = "redis-provider")
public interface RedisRemoteService {

    @GetMapping("/redis/setkey")
    public ResultEntity<String> setKey(@RequestParam("redisKey") String redisKey, @RequestParam("redisValue") String redisValue,
                                       @RequestParam("timeout") Integer timeout);
    @GetMapping("/redis/getvalue")
    public ResultEntity<String> getValue(@RequestParam("redisKey") String redisKey);

    @GetMapping("/redis/removekey")
    public ResultEntity<String> removeKey(@RequestParam("redisKey") String redisKey);

}
