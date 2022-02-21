package com.example.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {
	
	private static final String MY_QUEUE="MyQueue";
	
	@Bean
	Queue myQueue() {
		return new Queue(MY_QUEUE,true);
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setPassword("guest");
		return cachingConnectionFactory;
	}
	
	@Bean
	public MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer smplc = new SimpleMessageListenerContainer();
		smplc.setConnectionFactory(connectionFactory());
		smplc.setQueues(myQueue());
		smplc.setMessageListener(new RabbitMDQMessageListener());
		return smplc;
	}
	

}
