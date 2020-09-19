package edu.miu.aservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller2 {
	@Autowired
	OAuth2RestTemplate restTemplate;

	@GetMapping("/publicinfo")
	public String getPublicInfo() {
		return restTemplate.getForObject("http://localhost:8091/publicinfo", String.class);
	}
	
	@GetMapping("/userinfo")
	public String getUserInfo() {
		return restTemplate.getForObject("http://localhost:8091/userinfo", String.class);

	}

	@GetMapping("/managerinfo")
	public String getManagerInfo() {
		return restTemplate.getForObject("http://localhost:8091/managerinfo", String.class);

	}
}
