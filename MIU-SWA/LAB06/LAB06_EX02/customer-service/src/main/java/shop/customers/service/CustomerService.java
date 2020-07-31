package shop.customers.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.customers.domain.Customer;
import shop.customers.repository.CustomerRepository;
import shop.customers.service.dto.CustomerAdapter;
import shop.customers.service.dto.CustomerDTO;
import shop.customers.service.dto.OrderCustomerAdapter;
import shop.customers.service.dto.OrderCustomerDTO;


@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;	
	
	public CustomerDTO addCustomer(CustomerDTO customer) {
		return CustomerAdapter.getCustomerDTO(customerRepository.save(CustomerAdapter.getCustomer(customer)));
	}

	public CustomerDTO getCustomer(String customerNumber) {
	  Optional<Customer> customerOptional = customerRepository.findById(customerNumber);
	  if (customerOptional.isPresent()) {
		  Customer customer = customerOptional.get();
		  return CustomerAdapter.getCustomerDTO(customer);
	  }
	  else
		  return null;
  }

	public OrderCustomerDTO getOrderCustomer(String customerNumber) {
		  Optional<Customer> customerOptional = customerRepository.findById(customerNumber);
		  if (customerOptional.isPresent()) {
			  Customer customer = customerOptional.get();
			  return OrderCustomerAdapter.getOrderCustomerDTO(customer);
		  }
		  else
			  return null;
	}
}
