package cn.leon.service;

import cn.leon.entity.MemberPo;
import cn.leon.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Leon
 * @create 2022-05-11 1:16
 * IntelliJ IDEA
 * cn.leon.service
 */
@FeignClient("database-provider")
public interface DatabaseRemoteService {

    /**
     * 根据loginAcct查询用户数量
     * @param loginAcct
     * @return
     */
    @GetMapping("/database/queryUserByLoginacct")
    public ResultEntity<List<MemberPo>> queryUserByLoginacct(@RequestParam("loginAcct") String loginAcct);

    /**
     * 将用户信息存入mysql里面
     * @param member
     * @return
     */
    @PostMapping("/database/insertmember")
    public ResultEntity insertMember(@RequestBody MemberPo member);
}
