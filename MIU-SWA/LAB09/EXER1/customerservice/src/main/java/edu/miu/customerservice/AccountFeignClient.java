package edu.miu.customerservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("AccountService")
public interface AccountFeignClient {
    @GetMapping("/account/{customerid}")
    public Account getName(@PathVariable("customerid") String customerId);

}
