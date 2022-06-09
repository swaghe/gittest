package com.example.javahighconcurrency.consumer;

import com.example.javahighconcurrency.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BackUpConsumer {


    @RabbitListener(queues = RabbitMQConfig.BACK_QUEUE)
    public void backUpMessage(Message message, Channel channel) {
        String msg = new String(message.getBody());
        log.info("备份队列收到：{}", msg);
    }


    @RabbitListener(queues = RabbitMQConfig.LOG_QUEUE)
    public void logMessage(Message message, Channel channel) {
        String msg = new String(message.getBody());
        log.error("log记录错误：{}", msg);
    }
}
