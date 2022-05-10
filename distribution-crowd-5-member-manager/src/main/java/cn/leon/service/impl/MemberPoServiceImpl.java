package cn.leon.service.impl;

import cn.leon.entity.MemberPo;
import cn.leon.entity.MemberPoExample;
import cn.leon.entity.ResultEntity;
import cn.leon.service.MemberPoService;
import cn.leon.service.RedisOperationRemoteService;
import cn.leon.utils.CrowdUtils;
import cn.leon.utils.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Leon
 * @create 2022-05-10 17:12
 * IntelliJ IDEA
 * cn.leon.service.impl
 */
@Service
public class MemberPoServiceImpl implements MemberPoService {

//    @Resource
//    private MemberPoMapper memberPoMapper;

    @Resource
    private RedisOperationRemoteService remoteService;



    /**
     * 注册
     * @param phone
     * @param code
     * @return
     */
    public ResultEntity regist(String phone,String code,MemberPo memberPo) {

       /* //校验验证码
        String value = (String)remoteService.getValue(phone);
        if (value==null||value==""||value=="nill"){
            return ResultEntity.faildWithOutData("验证码过期");
        }

        if (value!=code){
            return ResultEntity.faildWithOutData("验证码错误");
        }

        //注册账户，存入数据库
        int insert = memberPoMapper.insert(memberPo);

        if (insert<=0){
            return ResultEntity.faildWithOutData("注册失败");
        }


        return ResultEntity.successWithOutData();*/

       return null;

    }
}
