package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RefreshScope
@RestController
public class CustomerGWController {
	
	@Value("${customerservice.url}")
	private String customerServiceUrl;
	
	@Value("${customerservice.name}")
	private String customerServiceName;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(path="findAllCustomers",produces=MediaType.APPLICATION_JSON_VALUE)
	public String findCustomers(){
		System.out.println(customerServiceName);
		return restTemplate.getForObject("http://"+customerServiceUrl.toUpperCase() + "/findall",
				String.class);
	}
	

}
