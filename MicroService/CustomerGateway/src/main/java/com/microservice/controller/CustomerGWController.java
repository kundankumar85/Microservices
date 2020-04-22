package com.microservice.controller;

import com.microservice.client.CustomerServiceClient;
import com.microservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RefreshScope
@RestController
public class CustomerGWController {
	
	@Value("${customerservice.url}")
	private String customerServiceUrl;
	
	@Value("${customerservice.name}")
	private String customerServiceName;

	@Autowired
	private CustomerServiceClient customerServiceClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(path="findAllCustomers",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findCustomers(){
		System.out.println(customerServiceName);
		/*return restTemplate.getForObject("http://"+customerServiceUrl.toUpperCase() + "/findall",
				String.class);*/

		return customerServiceClient.getAllCustomers();
	}

	@PostMapping(path="createCustomer",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addCustomer(@RequestBody Customer customer){
		customerServiceClient.addCustomer(customer);
	}
	

}
