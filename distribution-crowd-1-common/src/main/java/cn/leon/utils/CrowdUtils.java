package cn.leon.utils;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Leon
 * @create 2022-05-10 16:09
 * IntelliJ IDEA
 * cn.leon.utils
 */
public class CrowdUtils {
    /**
     * 集合验证
     * @param collection
     * @param <E>
     * @return
     */
    public static <E> boolean collectionEffectiveCheck(Collection<E> collection){
        return collection!=null&&collection.size()>0;
    }

    /**
     * 字符串验证
     * @param string
     * @param <E>
     * @return
     */
    public static <E> boolean stringEffectiveCheck(String string){
        return string!=null&&string.length()>0;
    }

    /**
     * 生成随机验证码
     * @return
     */
    public static String randomCode(){


        int code = 0;
        for (int i = 0; i < 6; i++) {
            int random = (int)(Math.random()*10);
            code=random+code*10;

        }
        return code+"";



    }

    public static void main(String[] args) {
        String s = CrowdUtils.randomCode();
        System.out.println(s);
    }
}
