package com.example.javahighconcurrency.consumer;


import com.example.javahighconcurrency.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeadLetterConsumer {

    @RabbitListener(queues = RabbitMQConfig.DEAD_QUEUE)
    public void deadLetterMessage(Message message, Channel channel) {
        String msg = new String(message.getBody());
        log.info("死信队列收到：{}", msg);
    }
}
