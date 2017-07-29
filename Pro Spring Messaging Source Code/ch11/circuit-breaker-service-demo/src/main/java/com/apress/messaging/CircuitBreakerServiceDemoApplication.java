package com.apress.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@SpringBootApplication
@EnableCircuitBreaker
public class CircuitBreakerServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerServiceDemoApplication.class, args);
	}
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@GetMapping("/")
	public String getMessageFromRemoteServer(){
		return this.getMessage();
	}
	
	@HystrixCommand(fallbackMethod = "defaultMessage")
	public String getMessage(){
	  	return restTemplate.getForObject("http://simple-service" + "/message", String.class); 
	}
	
	public String defaultMessage(){
		return "Nothing here";
	}

}
