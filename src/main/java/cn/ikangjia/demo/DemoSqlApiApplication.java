package cn.ikangjia.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ikangjia.demo.domain.mapper")
public class DemoSqlApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSqlApiApplication.class, args);
    }

}
