package pl.dom.RabbitM.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.dom.RabbitMQ.RabbitMQJSONProducer;
import pl.dom.RabbitMQ.RabbitMQMessageProducer;
import pl.dom.RabbitMQ.model.Klient;

@RestController
@RequestMapping("/prod")
public class RabbitMQRestController {

	private RabbitMQMessageProducer producer;
	
	@Autowired
	private RabbitMQJSONProducer jsonProducer;
	
	public RabbitMQRestController(RabbitMQMessageProducer producer) {
		this.producer = producer;
	}

	@GetMapping("/welcome")
	public String ohHI() {
		return "Welcome!";
	}
	
	@GetMapping("/send")
	public ResponseEntity<String> addMessage(@RequestParam("message") String msg ){
		 System.out.println("REST called - single " + msg);
		 producer.sendMessage(msg);
		 return ResponseEntity.ok("Msg to queue -> OK!");
	}
	
	
	@GetMapping("/sends/{messages}") //FANOUT
	public ResponseEntity<String> addMessages(@PathVariable("messages") String msg){
		System.out.println("REST called - fanout (many) " +msg);
		 producer.sendMessages(msg);
		 return ResponseEntity.ok("Msg to queues -> OK!");
	}
	
	@PostMapping(value="/klient", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addJSONMessage(@RequestBody Klient client){
		System.out.println("REST called - json: " + client);
		jsonProducer.sendJSONMessage(client);
		return ResponseEntity.ok("POST message -> ok!!");
	}
	
	
}
