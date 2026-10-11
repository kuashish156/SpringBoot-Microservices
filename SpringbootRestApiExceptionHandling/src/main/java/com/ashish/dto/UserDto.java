package com.ashish.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	 private Integer userId;
	 
	 @NotBlank(message = "First name is required")
	 @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
     private String  yourFirstName;
	 
	 @NotBlank(message = "Last name is required")
	 @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
     private String  yourLastName;
	 
	 @NotBlank(message = "Email is required")
	 @Email(message = "Please enter a valid email address")
     private String  emailAddress;
	 
	 @NotBlank(message = "Phone number is required")
	 @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain exactly 10 digits")       
     private String  phoneNumber;  
     
     public UserDto() {
		// TODO Auto-generated constructor stub
	}

	 public Integer getUserId() {
		 return userId;
	 }

	 public void setUserId(Integer userId) {
		 this.userId = userId;
	 }

	 public String getYourFirstName() {
		 return yourFirstName;
	 }

	 public void setYourFirstName(String yourFirstName) {
		 this.yourFirstName = yourFirstName;
	 }

	 public String getYourLastName() {
		 return yourLastName;
	 }

	 public void setYourLastName(String yourLastName) {
		 this.yourLastName = yourLastName;
	 }

	 public String getEmailAddress() {
		 return emailAddress;
	 }

	 public void setEmailAddress(String emailAddress) {
		 this.emailAddress = emailAddress;
	 }

	 public String getPhoneNumber() {
		 return phoneNumber;
	 }

	 public void setPhoneNumber(String phoneNumber) {
		 this.phoneNumber = phoneNumber;
	 }

	 
     
     
     

}
