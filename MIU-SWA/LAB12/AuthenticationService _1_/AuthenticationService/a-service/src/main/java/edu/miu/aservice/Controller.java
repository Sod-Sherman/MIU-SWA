package edu.miu.aservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/name")
	public String getName() {
		return "Frank Brown";
	}
	
	@GetMapping("/salary")
	public String getGetSalary() {
		return "95.000";
	}
	
	@GetMapping("/phone")
	public String getPhone() {
		return "645322899";
	}

}
