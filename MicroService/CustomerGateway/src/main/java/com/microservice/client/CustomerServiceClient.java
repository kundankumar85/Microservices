package com.microservice.client;

import com.microservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {

    @GetMapping(path="/findall", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers();

    @PostMapping(path="/create", produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addCustomer(@RequestBody Customer customer);
}
