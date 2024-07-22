package com.javateche.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

	 @Bean
	  public NewTopic createTobil() {
		  //To Create a Topic
		  //TopicName,Partition,replicationFactor
		  return new NewTopic("javaTeche-demo11" ,5,(short)1);
	  }
}
