package cn.leon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Leon
 * @create 2022-05-10 1:04
 * IntelliJ IDEA
 * cn.leon
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.leon.mapper")
public class MainClassDatabase {
    public static void main(String[] args){
        SpringApplication.run(MainClassDatabase.class, args);
    }
}
