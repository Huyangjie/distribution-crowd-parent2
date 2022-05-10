package cn.leon.service;

import cn.leon.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leon
 * @create 2022-05-10 17:21
 * IntelliJ IDEA
 * cn.leon.service
 */
@FeignClient(value = "redis-provider")
public interface RedisOperationRemoteService {

    @GetMapping("/redis/setkey")
    public ResultEntity<String> setKey(@RequestParam("redisKey") String redisKey,@RequestParam("redisValue") String redisValue,
                               @RequestParam("timeout") Integer timeout);
    @GetMapping("/redis/getvalue")
    public ResultEntity getValue(@RequestParam("redisKey") String redisKey);

    @GetMapping("/redis/removekey")
    public ResultEntity removeKey(@RequestParam("redisKey") String redisKey);


}
