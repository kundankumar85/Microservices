package com.microservice.model;

public class Customer {
	
	private Integer Id ;
	private String name;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(Integer id,String name) {
		this.Id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
