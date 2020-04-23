package com.microservice.controller;

import com.microservice.client.CustomerServiceClient;
import com.microservice.model.Customer;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RefreshScope
@RestController
@Api(tags = "Customer-Gateway Controller")
@Slf4j
public class CustomerGWController {
	
	@Value("${customerservice.url}")
	private String customerServiceUrl;
	
	@Value("${customerservice.name}")
	private String customerServiceName;

	@Autowired
	private CustomerServiceClient customerServiceClient;
	
	@Autowired
	private RestTemplate restTemplate;


	@ApiOperation(tags = {"controller"}, value = "Customer-Gateway Controller", notes = "This is a operation for Customer Service", response = List.class,
			responseHeaders = {
					@ResponseHeader(name = "API-BridgeProcessTime", description = "Time taken to process in API Bridge", response = Integer.class),
					@ResponseHeader(name = "API-Bridge-TimeStamp", description = "Timestamp for request received in API Bridge", response = LocalDateTime.class)
			})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Created"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error while processing") })
	@GetMapping(path="findAllCustomers",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findCustomers(){
	log.info("find all customer");
		/*return restTemplate.getForObject("http://"+customerServiceUrl.toUpperCase() + "/findall",
				String.class);*/

		return customerServiceClient.getAllCustomers();
	}

	@PostMapping(path="createCustomer",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addCustomer(@RequestBody Customer customer){
        log.info("add customer");
		customerServiceClient.addCustomer(customer);
	}
	

}
