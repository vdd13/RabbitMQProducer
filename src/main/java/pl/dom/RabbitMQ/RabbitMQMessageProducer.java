package pl.dom.RabbitMQ;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQMessageProducer {

	@Value("${dom.exchange}")
	private String exchangeName;
	
	@Value("${dom.key}")
	private String routingKeyName;
	
	@Value("${dom.exchange.fanout}")
	private String fanoutName;
		
	private RabbitTemplate rabbitTemplate;
	
	public RabbitMQMessageProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	//1 wiadomosc na kolejke odczytywana przez jednego consumer
	public void sendMessage(String msg) {
		System.out.println("MESSAGE PRODUCED: " + msg);
		rabbitTemplate.convertAndSend(exchangeName, routingKeyName, msg);
		
		System.out.println("Exchange : " + rabbitTemplate.getExchange() + ", Queue :  " +  rabbitTemplate.getDefaultReceiveQueue() + ",  key : " + rabbitTemplate.getRoutingKey());
	}
	
	//1 wiadomosc na kolejke odczytywana przez wielu consumer -> FANOUT wiele kolejek pod jednym exchange
	public void sendMessages(String msg) {
		System.out.println("MESSAGESSS PRODUCED: " + msg);
		rabbitTemplate.convertAndSend(fanoutName, "", msg);
	}
	
}
