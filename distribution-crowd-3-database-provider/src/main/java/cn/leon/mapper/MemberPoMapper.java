package cn.leon.mapper;

import cn.leon.entity.MemberPo;
import cn.leon.entity.MemberPoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MemberPoMapper {
    int countByExample(MemberPoExample example);

    int deleteByExample(MemberPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPo record);

    int insertSelective(MemberPo record);

    List<MemberPo> selectByExample(MemberPoExample example);

    MemberPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPo record, @Param("example") MemberPoExample example);

    int updateByExample(@Param("record") MemberPo record, @Param("example") MemberPoExample example);

    int updateByPrimaryKeySelective(MemberPo record);

    int updateByPrimaryKey(MemberPo record);
}