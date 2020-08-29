package edu.miu.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    AccountFeignClient accountFeignClient;

    @GetMapping("/customer/{customerid}")
    public Account getName(@PathVariable("customerid") String customerId) {
        Account account = accountFeignClient.getName(customerId);
        return account;
    }
}
