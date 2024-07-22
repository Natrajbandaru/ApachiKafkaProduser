package com.javateche.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javateche.model.Customer;
import com.javateche.service.KafkaMessagePublisher;

@RestController
@RequestMapping("/producer-app")
public class EventController {
	
	@Autowired
	private KafkaMessagePublisher publisher;

	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publicMessage(@PathVariable String message){
		System.out.println("Enterin");
		try {
			publisher.sendMessageTopic(message);
			return ResponseEntity.ok("Message Published successfully .. ");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	// Serialization and deSerialization Methods in kafka to send a object
	@PostMapping("/publishCustomer")
	public ResponseEntity<?> serialiaztioAObject(@RequestBody Customer customer){
		try {
			publisher.sendMessageTopicInObject(customer);
			return ResponseEntity.ok("Serialization Message Published successfully .. ");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
