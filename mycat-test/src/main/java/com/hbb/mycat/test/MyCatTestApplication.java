package com.hbb.mycat.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author hjc
 * @Date 2022-06-09-14-39
 **/
@SpringBootApplication
@MapperScan("com.hbb.mycat.test.mapper")
public class MyCatTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCatTestApplication.class, args);
    }
}
