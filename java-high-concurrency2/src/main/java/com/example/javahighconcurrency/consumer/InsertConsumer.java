package com.example.javahighconcurrency.consumer;

import com.example.javahighconcurrency.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class InsertConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("收到消息：{}", msg);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage2(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("收到消息2：{}", msg);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }


}
