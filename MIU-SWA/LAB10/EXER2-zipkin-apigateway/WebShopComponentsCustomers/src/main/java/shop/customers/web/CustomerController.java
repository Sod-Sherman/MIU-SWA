package shop.customers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import shop.customers.service.CustomerDTO;
import shop.customers.service.CustomerService;
import shop.customers.service.OrderCustomerDTO;

@RestController
@CrossOrigin(maxAge = 3600)
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<CustomerDTO>(HttpStatus.OK);
    }

    @GetMapping("/customer/{customerNumber}")
    public ResponseEntity<?> getCustomer(@PathVariable String customerNumber) {
        return new ResponseEntity<CustomerDTO>(customerService.getCustomer(customerNumber), HttpStatus.OK);
    }

    @GetMapping("/ordercustomer/{customerNumber}")
    public ResponseEntity<?> getOrderCustomer(@PathVariable String customerNumber) {
        return new ResponseEntity<OrderCustomerDTO>(customerService.getOrderCustomer(customerNumber), HttpStatus.OK);
    }
}
