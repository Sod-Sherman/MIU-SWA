package edu.miu.aservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
A-Service Main Controller
 that calls B and C services accordingly by authorization
 */
@RestController
public class IntegratedController {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @RequestMapping(value = {"/productdata", "/products", "/phone"}, method = RequestMethod.GET)
    public String getPhone() {
        // Calling B service
        return restTemplate.getForObject("http://localhost:8091/phone", String.class);
    }

    @GetMapping("/salary")
    public String getGetSalary() {
        // Calling C service
        return restTemplate.getForObject("http://localhost:8092/salary", String.class);
    }
}
