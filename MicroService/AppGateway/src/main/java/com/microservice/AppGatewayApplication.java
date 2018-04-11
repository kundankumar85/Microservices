package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.microservice.filter.ErrorFilter;
import com.microservice.filter.PostFilter;
import com.microservice.filter.PreFilter;
import com.microservice.filter.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class AppGatewayApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "api-gateway");
		SpringApplication.run(AppGatewayApplication.class, args);
	}
	
	
	@Bean
	public PreFilter preFilter(){
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter(){
		return new PostFilter();
	}
	@Bean
	public RouteFilter routeFilter(){
		return new RouteFilter();
	}
	@Bean
	public ErrorFilter errorFilter(){
		return new ErrorFilter();
	}
}
