package com.javateche.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.javateche.model.Customer;

@Service
public class KafkaMessagePublisher {
	
	@Autowired
	private KafkaTemplate<String, Object> templet;
	
	public void sendMessageTopic(String message) {
		System.out.println("publish1"+message);
		try {
			for(int i=0;i<10000000;i++) {
				
				CompletableFuture<SendResult<String, Object>> future =
						templet.send("javaTeche-demo8",message+i);
				System.out.println("publish2");
				
				future.whenComplete((result,ex)->{
					if(ex==null) {
						System.out.println("Sent Message=["
								+ ""+message+ ""+result.getRecordMetadata().offset()+"]");
					}else {
						System.out.println("Unable to send Message=["
								+ ""+message+ ""+ex.getMessage());
					}
				});
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Serialization and deSerialization Methods in kafak to send a object
	public void sendMessageTopicInObject(Customer customer) {
		System.out.println("publish1"+customer);
		try {
			for(int i=0;i<1000;i++) {
				CompletableFuture<SendResult<String, Object>> future =
						templet.send("javaTeche-demo11",customer);
				System.out.println("publish2");
				
				future.whenComplete((result,ex)->{
					if(ex==null) {
						System.out.println("Sent Message=["
								+ ""+customer.toString()+ ""+result.getRecordMetadata().offset()+"]");
					}else {
						System.out.println("Unable to send Message=["
								+ ""+customer.toString()+ ""+ex.getMessage());
					}
				});
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
