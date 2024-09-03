package pl.dom.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.dom.RabbitMQ.model.Klient;

@Service
public class RabbitMQJSONProducer {

	@Value("${dom.exchange}")
	private String exchange;
	
	@Value("${dom.key.json}")
	private String routingJsonKeyName;
	
	private RabbitTemplate rabbitTemplate;

	public RabbitMQJSONProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	} 

	public void sendJSONMessage(Klient klient) {
		rabbitTemplate.convertAndSend(exchange, routingJsonKeyName, klient);
	}
	
}
