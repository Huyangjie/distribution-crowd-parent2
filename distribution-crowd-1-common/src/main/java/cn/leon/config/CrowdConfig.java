package cn.leon.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Leon
 * @create 2022-05-10 18:25
 * IntelliJ IDEA
 * cn.leon.config
 */
@Configuration
public class CrowdConfig {

//    @Bean
//    public Logger.Level sl4jLoggerLever(){
//        return Logger.Level.FULL;
//    }

    @Bean
    public BCryptPasswordEncoder getBcrpyPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
