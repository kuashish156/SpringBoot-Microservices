package com.example.demo;

import lombok.Data;

@Data
public class User {
	
	private String name;
	
	
	public static void main(String[] args) {
		
		User u = new User();
		u.setName("ashish");
		
	}
	

}
