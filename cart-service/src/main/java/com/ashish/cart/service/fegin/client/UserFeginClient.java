package com.ashish.cart.service.fegin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.ashish.cart.service.dto.UserDto;



@FeignClient(name = "USER-SERVICE" , path="/api/v1/users")
public interface UserFeginClient {
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> fetchUser(@PathVariable ("userId") Integer userId);

}


/**  
 * If you don't use Eureka, you can configure FeignClient with a direct URL, 
 * load balancer URL, or Kubernetes DNS name.
 * 
 */