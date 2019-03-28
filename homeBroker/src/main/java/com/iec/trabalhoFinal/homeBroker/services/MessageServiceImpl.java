package com.iec.trabalhoFinal.homeBroker.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.iec.trabalhoFinal.homeBroker.config.RabbitMQConfig;
import com.iec.trabalhoFinal.homeBroker.domain.Message;

@Service
public class MessageServiceImpl implements IMessageService{

	private final RabbitTemplate rabbitTemplate;
	 
    public MessageServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    @Override
    public void sendMessage(Message message) {
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_MESSAGES, message);
    }	
}
