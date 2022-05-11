package cn.leon.controller;

import cn.leon.entity.MemberPo;
import cn.leon.entity.ResultEntity;
import cn.leon.entity.vo.MemberLoginVo;
import cn.leon.entity.vo.MemberVo;
import cn.leon.service.DatabaseRemoteService;
import cn.leon.service.MemberPoService;
import cn.leon.service.RedisRemoteService;
import cn.leon.utils.CrowdUtils;
import cn.leon.utils.VerificationCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @author Leon
 * @create 2022-05-10 18:07
 * IntelliJ IDEA
 * cn.leon.controller
 */
@RestController
@Slf4j
public class MemberPoController {


    @Autowired
    private MemberPoService memberPoService;

    @Autowired
    private DatabaseRemoteService databaseService;

    @Autowired
    private RedisRemoteService redisService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
            log.info("发送验证码前=====");

            log.info(code);
            VerificationCode.sendCode(phone, code);
            log.info("发送验证码后=====");

            return ResultEntity.successWithOutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.faildWithOutData(e.getMessage());
        }

    }


    /**
     * 将用户信息membervo封装成MemberPo存入数据库
     * @param memberVO
     * @return
     */
    @RequestMapping("/member/saveMember")
    public ResultEntity regist(@RequestBody MemberVo memberVO){

        //检查用户名有效性
        String loginAcct = memberVO.getLoginAcct();
        if (!CrowdUtils.stringEffectiveCheck(loginAcct)){
            return ResultEntity.faildWithOutData("用户名为空");
        }

        //获取redis中验证码
        ResultEntity<String> resultEntityRedis = redisService.getValue("VERIFY_CODE_"+memberVO.getPhone());

        if (resultEntityRedis.getCode().equals(ResultEntity.FAILD)){
            return resultEntityRedis;
        }

        String redisCode = resultEntityRedis.getData();

        log.info("===redisCode验证码"+redisCode);
        System.out.println("===redisCode验证码==="+redisCode);
        //比较验证码
        if (redisCode==null||redisCode==""){
            return ResultEntity.faildWithOutData("验证码已过期");
        }

        if (!redisCode.equals(memberVO.getCode())){
            return ResultEntity.faildWithOutData("验证码有误");
        }

        //检查账户是否存在
        ResultEntity<List<MemberPo>> resultEntityMember = databaseService.queryUserByLoginacct(memberVO.getLoginAcct());

        if (resultEntityMember.getCode().equals(ResultEntity.FAILD)){
            return resultEntityMember;
        }

        List<MemberPo> data = resultEntityMember.getData();
        if (data.size()>0){
            return ResultEntity.faildWithOutData("该账户已存在");
        }

        //加密
        String password = memberVO.getPassword();
        password = bCryptPasswordEncoder.encode(password);
        memberVO.setPassword(password);
        MemberPo memberPo = new MemberPo();

        BeanUtils.copyProperties(memberVO, memberPo);

        ResultEntity resultEntityDatabase = databaseService.insertMember(memberPo);
        if (resultEntityDatabase.getCode().equals(ResultEntity.FAILD)){
            return resultEntityDatabase;
        }
        return ResultEntity.successWithOutData();


    }

    /**
     * 登录
     * @param loginacct
     * @param password
     * @param session
     * @return
     */
    @GetMapping("/member/login")
    public ResultEntity login(@RequestParam("loginacct") String loginacct,
                              @RequestParam("password") String password, HttpSession session){
        //验证账号密码有效性
        if (!CrowdUtils.stringEffectiveCheck(loginacct)||!CrowdUtils.stringEffectiveCheck(password)){
            return ResultEntity.faildWithOutData("账户密码为空");
        }

        ResultEntity<List<MemberPo>> listResultEntity = databaseService.queryUserByLoginacct(loginacct);

        if (listResultEntity.getCode().equals(ResultEntity.FAILD)){
            return listResultEntity;
        }

        List<MemberPo> memberPoList = listResultEntity.getData();

        //判断账户是否存在
        if (memberPoList.size()<0){
            return ResultEntity.faildWithOutData("账户不存在");
    }

        //验证密码
        MemberPo memberPo = memberPoList.get(0);

        //获取数据库密码
        String userpswd = memberPo.getUserpswd();

        log.info(userpswd+"======数据库密码======");
        //加密
        if(!bCryptPasswordEncoder.matches(password, userpswd)){
            return ResultEntity.faildWithOutData("密码不正确");
        }

        session.setAttribute("LOGIN_MEMBER",memberPo);

        return ResultEntity.successWithOutData();

        /*//生成令牌
        UUID uuid = UUID.randomUUID();
        String token = "MEMBER_LOGIN_"+uuid.toString().toUpperCase();

        //将令牌和memberId存入redis
        ResultEntity<String> stringResultEntity = redisService.setKey(token, memberPo.getId().toString(), -1);
        if (stringResultEntity.getCode().equals(ResultEntity.FAILD)){
            return stringResultEntity;
        }

        //封装MemberLoginVo
        MemberLoginVo memberLoginVo = new MemberLoginVo();
        memberLoginVo.setUsername(memberPo.getUsername());
        memberLoginVo.setEmail(memberPo.getEmail());
        memberLoginVo.setToken(token);
        //成功返回vo
        return ResultEntity.successWithData(memberLoginVo);
        */

    }

    @GetMapping("/member/loginout")
    public ResultEntity loginOut(HttpSession session){
        session.invalidate();
        return ResultEntity.successWithOutData();
    }


}
