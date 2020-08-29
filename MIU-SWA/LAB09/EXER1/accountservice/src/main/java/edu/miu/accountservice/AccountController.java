package edu.miu.accountservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/account/{customerid}")
    public Account getName(@PathVariable("customerid") String customerId) {
        return new Account("1234", "1000.00");
    }
}
