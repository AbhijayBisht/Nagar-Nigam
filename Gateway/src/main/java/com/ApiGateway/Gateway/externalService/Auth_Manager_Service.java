package com.ApiGateway.Gateway.externalService;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "Auth-Authentication-Manager", path = "api/auth/v1")
public interface Auth_Manager_Service {

	@GetMapping("/getRolesByUsername")
    public List<String> getRolesByUsername(@RequestParam("username") String username);
	
//	@GetMapping("/getUserByUsername")
//    public String getUserByUsername(@RequestParam("username") String username);
}
