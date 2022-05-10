package cn.leon.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leon
 * @create 2022-05-10 18:25
 * IntelliJ IDEA
 * cn.leon.config
 */
@Configuration
public class Sl4jLog {

    @Bean
    Logger.Level sl4jLoggerLever(){
        return Logger.Level.FULL;
    }
}
