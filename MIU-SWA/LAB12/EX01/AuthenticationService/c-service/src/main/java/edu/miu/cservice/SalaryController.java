package edu.miu.cservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {
    @GetMapping("/salary")
    public String getGetSalary() {
        return "Hello from Service - C and inside of GetSalary Method: $100,000/Annual";
    }
}
