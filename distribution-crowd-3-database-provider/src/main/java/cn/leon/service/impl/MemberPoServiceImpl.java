package cn.leon.service.impl;

import cn.leon.entity.MemberPo;
import cn.leon.entity.MemberPoExample;
import cn.leon.mapper.MemberPoMapper;
import cn.leon.service.MemberPoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Leon
 * @create 2022-05-11 1:02
 * IntelliJ IDEA
 * cn.leon.service.impl
 */
@Service
public class MemberPoServiceImpl implements MemberPoService {

    @Resource
    private MemberPoMapper memberPoMapper;


    public List<MemberPo> queryUseracct(String loginAcct) {
        MemberPoExample example = new MemberPoExample();
        example.createCriteria().andLoginacctEqualTo(loginAcct);
        return memberPoMapper.selectByExample(example);
/*
        List<MemberPo> memberPos = memberPoMapper.selectByExample(example);
        return memberPos.get(0);
*/
    }

    public int inserMember(MemberPo member) {
        return memberPoMapper.insert(member);
    }
}
