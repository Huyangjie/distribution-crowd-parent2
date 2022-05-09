package cn.leon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Leon
 * @create 2022-05-10 1:10
 * IntelliJ IDEA
 * cn.leon
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MainClass {
    public static void main(String[] args){
        SpringApplication.run(MainClass.class, args);
    }
}
