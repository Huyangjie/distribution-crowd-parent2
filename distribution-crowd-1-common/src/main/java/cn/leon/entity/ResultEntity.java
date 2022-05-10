package cn.leon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leon
 * @create 2022-05-10 16:24
 * IntelliJ IDEA
 * cn.leon.entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity <D>{

    private String code;
    private String message;
    private D data;


    public static final String NOMESSAGE="";
    public static final String SUCCESS="200";
    public static final String FAILD ="400";
    public static final String NODATA= "";

    public static <D> ResultEntity successWithData(D data){
        return new ResultEntity(SUCCESS,NOMESSAGE,data);
    }

    public static ResultEntity successWithOutData(){
        return new ResultEntity(SUCCESS,NOMESSAGE,NODATA);
    }

    public static ResultEntity faildWithOutData(String message){
        return new ResultEntity(FAILD,message,null);
    }

}
