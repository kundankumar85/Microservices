package com.microservice.controller;

import com.microservice.model.Customer;
import com.microservice.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(tags = "Customer Controller")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(path="/findall", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAllCustomers(){
		return customerService.findAll();
	}
	
	@PostMapping(path="/create", produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void addCustomer(@RequestBody Customer customer){
		customerService.createCustomer(customer);
	}


}
