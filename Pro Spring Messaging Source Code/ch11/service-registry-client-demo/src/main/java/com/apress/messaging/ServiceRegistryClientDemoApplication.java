package com.apress.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@SpringBootApplication
public class ServiceRegistryClientDemoApplication {
	private static Logger log = LoggerFactory.getLogger(ServiceRegistryClientDemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryClientDemoApplication.class, args);
	}
	
	EurekaClient discoveryClient;
	private RestTemplate restTemplate = new RestTemplate();
	
	ServiceRegistryClientDemoApplication(EurekaClient discoveryClient){
		this.discoveryClient = discoveryClient;
	}
	
	@GetMapping("/")
	public String getMessageFromRemoteServer(){
		return restTemplate.getForObject(fetchServiceUrl() + "/message", String.class); 
	}
	
	private String fetchServiceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("SIMPLE-SERVICE", false);
        String serviceUrl = instance.getHomePageUrl();
        log.info(">>> Accessing: " + serviceUrl);
        return serviceUrl;
    }
	
}
