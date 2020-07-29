package shop.order.service.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import shop.order.service.RestService;
import shop.order.service.dto.OrderCustomerDTO;

import java.io.IOException;
import java.net.URI;

@Service
public class CustomerService {

    @Autowired
    private RestService restService;

    @Value("${webshop.service.customer.url}")
    private String customerServiceUrl;

    public OrderCustomerDTO getOrderCustomer(String customerNumber) throws IOException {
        return restService.getValueOfRestCall(URI.create(customerServiceUrl + "/customer/order-customer/" + customerNumber),
                HttpMethod.GET, "", OrderCustomerDTO.class);
    }

}
