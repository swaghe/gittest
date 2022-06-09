package com.example;

import com.example.config.SpringConfig;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class SpringTestApplicationTests {

    @Test
    void contextLoads() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        context.getBean(Bird.class);
//        System.out.println(bean);
        context.close();
    }

}
