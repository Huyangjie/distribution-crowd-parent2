package cn.leon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Leon
 * @create 2022-05-10 1:25
 * IntelliJ IDEA
 * cn.leon
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MainClassMember {
    public static void main(String[] args){
        SpringApplication.run(MainClassMember.class, args);
    }
}
