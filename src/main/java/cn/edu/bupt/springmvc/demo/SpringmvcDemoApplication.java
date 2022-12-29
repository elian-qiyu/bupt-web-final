package cn.edu.bupt.springmvc.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.edu.bupt.springmvc.demo.mapper")
@SpringBootApplication
public class SpringmvcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcDemoApplication.class, args);
    }

}
