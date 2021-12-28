package org.csu.Information;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.csu.Information.dao")
@SpringBootApplication
public class UserInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserInformationApplication.class, args);
    }

}