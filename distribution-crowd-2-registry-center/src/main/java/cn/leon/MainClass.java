package cn.leon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Leon
 * @create 2022-05-10 1:02
 * IntelliJ IDEA
 * cn.leon
 */
@SpringBootApplication
@EnableEurekaServer
public class MainClass {
    public static void main(String[] args){
        SpringApplication.run(MainClass.class, args);
    }
}
