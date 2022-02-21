package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMDQMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		System.out.println("messaage "+new String(message.getBody()));
	}

}
