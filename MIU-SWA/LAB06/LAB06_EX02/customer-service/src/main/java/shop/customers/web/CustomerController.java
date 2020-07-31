package shop.customers.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shop.customers.service.dto.CustomerDTO;
import shop.customers.service.CustomerService;
import shop.customers.service.dto.OrderCustomerDTO;

@RestController
@Log4j2
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerNumber}")
    public ResponseEntity<?> getCustomer(@PathVariable String customerNumber) {
        return new ResponseEntity<>(customerService.getCustomer(customerNumber), HttpStatus.OK);
    }

    @GetMapping("/customer/order-customer/{customerNumber}")
    public ResponseEntity<?> getOrderCustomer(@PathVariable String customerNumber) {
        OrderCustomerDTO orderCustomerDTO = customerService.getOrderCustomer(customerNumber);
        log.info(orderCustomerDTO);
        return new ResponseEntity<>(orderCustomerDTO, HttpStatus.OK);
    }
}
