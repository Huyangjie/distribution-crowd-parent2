package cn.leon.service;

import cn.leon.entity.MemberPo;
import cn.leon.entity.ResultEntity;

/**
 * @author Leon
 * @create 2022-05-10 17:11
 * IntelliJ IDEA
 * cn.leon.service
 */
public interface MemberPoService {

    public ResultEntity regist(String phone, String code, MemberPo memberPo);
}
