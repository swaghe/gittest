package com.example.javahighconcurrency.controller;

import com.example.javahighconcurrency.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.UUID;

@RestController
public class ProductController {

    @Resource
    private RabbitTemplate rabbitTemplate;


    @GetMapping("insert")
    public void insert() {
        String name = UUID.randomUUID().toString().replace("-", "").substring(0, 9);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, name);
    }

}
