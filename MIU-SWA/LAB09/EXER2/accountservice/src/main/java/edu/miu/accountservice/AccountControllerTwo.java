package edu.miu.accountservice;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("Two")
public class AccountControllerTwo {

    @GetMapping("/account/{customerid}")
    public Account getName(@PathVariable("customerid") String customerId) {
        System.out.println("<<<<<<----- [AccountControllerTwo called]");
        return new Account("1234", "1000.00 <<<<<<----- [AccountControllerTwo called]");
    }
}
