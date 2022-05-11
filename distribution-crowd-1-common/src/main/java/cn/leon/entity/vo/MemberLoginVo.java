package cn.leon.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leon
 * @create 2022-05-11 23:48
 * IntelliJ IDEA
 * cn.leon.entity.vo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginVo {

    private String username;

    private String email;

    private String token;


}
