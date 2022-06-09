package com.example.config;

import com.example.pojo.Bird;
import com.example.pojo.MyBeanPostProcessor;
import com.example.pojo.User;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author hjc
 * @Date 2022-05-17-23-15
 **/
@Configuration
public class SpringConfig {

    //    @Bean(initMethod = "init",destroyMethod = "destory")
    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

//    @Bean
//    public Bird bird() {
//        return new Bird();
//    }
}
