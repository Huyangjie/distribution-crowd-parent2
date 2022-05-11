package cn.leon.controller;

import cn.leon.entity.ResultEntity;
import cn.leon.utils.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Leon
 * @create 2022-05-10 17:15
 * IntelliJ IDEA
 * cn.leon.redis
 */
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 向Redis存入数据
     * @param redisKey
     * @param redisValue
     */
    @GetMapping("/redis/setkey")
    public ResultEntity<String> setKey(@RequestParam("redisKey") String redisKey,
                                       @RequestParam("redisValue") String redisValue,
                                       @RequestParam("timeout") Integer timeout){
        if (!CrowdUtils.stringEffectiveCheck(redisKey)||!CrowdUtils.stringEffectiveCheck(redisValue)){
            return ResultEntity.faildWithOutData("格式不正确");
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        try {
            if (timeout==null||timeout==-1){
                operations.set(redisKey, redisValue);
            }else {
                operations.set(redisKey, redisValue,timeout,TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.faildWithOutData(e.getMessage());
        }
        return ResultEntity.successWithOutData();
    }

    @GetMapping("/redis/getvalue")
    public ResultEntity<String> getValue(@RequestParam("redisKey") String redisKey){
        if (!CrowdUtils.stringEffectiveCheck(redisKey)){
            return ResultEntity.faildWithOutData("格式不正确");
        }
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String value = operations.get(redisKey);

            return ResultEntity.successWithData(value);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.faildWithOutData(e.getMessage());
        }
    }


    @GetMapping("/redis/removekey")
    public ResultEntity<String> removeKey(@RequestParam("redisKey") String redisKey){
        if (!CrowdUtils.stringEffectiveCheck(redisKey)){
            return ResultEntity.faildWithOutData("格式不正确");
        }
        try {
            redisTemplate.delete(redisKey);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.faildWithOutData(e.getMessage());
        }
        return ResultEntity.successWithOutData();
    }
}
