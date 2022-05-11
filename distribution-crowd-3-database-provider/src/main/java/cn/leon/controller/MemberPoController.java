package cn.leon.controller;

import cn.leon.entity.MemberPo;
import cn.leon.entity.ResultEntity;
import cn.leon.service.MemberPoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leon
 * @create 2022-05-11 1:03
 * IntelliJ IDEA
 * cn.leon.controller
 */
@RestController
public class MemberPoController {

    @Autowired
    private MemberPoService memberPoService;

    /**
     * 根据loginAcct查询用户数量
     * @param loginAcct
     * @return
     */
    @GetMapping("/database/queryUserByLoginacct")
    public ResultEntity<List<MemberPo>> queryUserByLoginacct(@RequestParam("loginAcct") String loginAcct){
        try {
            List<MemberPo> memberPos = memberPoService.queryUseracct(loginAcct);
            return ResultEntity.successWithData(memberPos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.faildWithOutData(e.getMessage());
        }

    }


    /**
     * 将用户信息存入mysql
     * @param member
     * @return
     */
    @PostMapping("/database/insertmember")
    public ResultEntity insertMember(@RequestBody MemberPo member){
        try {
            int i = memberPoService.inserMember(member);
            return ResultEntity.successWithOutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.faildWithOutData(e.getMessage());
        }
    }



}
