package cn.leon.controller;

import cn.leon.entity.ResultEntity;
import cn.leon.service.MemberPoService;
import cn.leon.service.RedisOperationRemoteService;
import cn.leon.utils.CrowdUtils;
import cn.leon.utils.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Leon
 * @create 2022-05-10 18:07
 * IntelliJ IDEA
 * cn.leon.controller
 */
@RestController
//@Slf4j
public class MemberPoController {


    @Autowired
    private MemberPoService memberPoService;

    @Autowired
    private RedisOperationRemoteService redisService;

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @RequestMapping("/member/send/code")
    public ResultEntity sendCode(@RequestParam("phone") String phone){

        //校验手机格式
        if(!CrowdUtils.stringEffectiveCheck(phone)){
            return ResultEntity.faildWithOutData("手机号格式不正确");
        }

        //生成验证码
        String code = CrowdUtils.randomCode();

        try {
//            存入redis
            redisService.setKey("VERIFY_CODE_"+phone,code,600);
            new VerificationCode().sendCode(phone, code);
            return ResultEntity.successWithOutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.faildWithOutData(e.getMessage());
        }

    }


//    @RequestMapping("/member/verify/code")

}
