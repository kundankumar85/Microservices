package com.microservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.model.Customer;

@Service
public class CustomerService {
	
	private List<Customer> customers = new ArrayList<>();
	
	public List<Customer> findAll(){
		if(customers.isEmpty())
			return Arrays.asList(new Customer(1,"kundan"), new Customer(2, "Santos"));
		else
			return customers;
	}

	public void createCustomer(Customer customer) {
		customers.add(customer);
	}
	
	

}
