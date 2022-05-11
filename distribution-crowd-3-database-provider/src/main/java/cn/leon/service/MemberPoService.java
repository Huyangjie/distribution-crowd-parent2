package cn.leon.service;

import cn.leon.entity.MemberPo;

import java.util.List;

/**
 * @author Leon
 * @create 2022-05-11 1:01
 * IntelliJ IDEA
 * cn.leon.service
 */
public interface MemberPoService {
    List<MemberPo> queryUseracct(String loginAcct);

    int inserMember(MemberPo member);
}
