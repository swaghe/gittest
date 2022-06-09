package com.example.javahighconcurrency.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "directExchange";

    public static final String QUEUE_NAME = "insertQUEUE";

    public static final String ROUTING_KEY = "insert.queue";

    public static final String DEAD_EXCHANGE = "deadExchange";

    public static final String DEAD_QUEUE = "deadQueue";

    public static final String DEAD_ROUTING_KEY = "dead.queue";

    public static final String BACK_EXCHANGE = "backExchange";

    public static final String BACK_QUEUE = "backQueue";

    public static final String LOG_QUEUE = "logQUEUE";


    @Bean
    public DirectExchange directExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("alternate-exchange", BACK_EXCHANGE);
        return new DirectExchange(EXCHANGE_NAME, true, false, map);
    }

    @Bean
    public Queue insertQueue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-max-length", 4000);
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key", DEAD_ROUTING_KEY);
        return new Queue(QUEUE_NAME, true, false, false, map);
    }


    @Bean
    public Binding directExchangeBindInsertQueue() {
        return BindingBuilder.bind(insertQueue()).to(directExchange()).with(ROUTING_KEY);
    }


    //死信交换机和队列

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_EXCHANGE, true, false);
    }

    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-max-length", 3000);
        return new Queue(DEAD_QUEUE, true, false, false, map);
    }

    @Bean
    public Binding deadLetterBind() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_ROUTING_KEY);
    }

    //备份交换机

    @Bean
    public FanoutExchange backExchange() {
        return new FanoutExchange(BACK_EXCHANGE, true, false);
    }

    @Bean
    public Queue backQueue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-max-length", 4000);
        return new Queue(BACK_QUEUE, true, false, false, map);
    }

    @Bean
    Queue logQueue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-max-length", 4000);
        return new Queue(LOG_QUEUE, true, false, false, map);
    }

    @Bean
    public Binding backExchangeBindBackQueue() {
        return BindingBuilder.bind(backQueue()).to(backExchange());
    }

    @Bean
    public Binding backExchangeBindLogQueue() {
        return BindingBuilder.bind(logQueue()).to(backExchange());
    }


//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
////        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);             //开启手动 ack
//        return factory;
//    }


}
