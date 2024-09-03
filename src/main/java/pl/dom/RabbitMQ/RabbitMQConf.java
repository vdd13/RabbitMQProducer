package pl.dom.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConf {

	@Value("${dom.queue}")
	private String queueName;
	
	@Value("${dom.exchange}")
	private String exchangeName;
	
	@Value("${dom.key}")
	private String routingKeyName;

	@Value("${dom.queue.json}")
	private String jsonQueue;
	
	@Value("${dom.key.json}")
	private String routingJsonKeyName;
	
	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}
	
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}
	
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(exchangeName);
	}
	
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue())
				.to(topicExchange())
				.with(routingKeyName);
	}
	
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue())
				.to(topicExchange())
				.with(routingJsonKeyName);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
	
}
