package shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import shop.dto.AddressDTO;
import shop.dto.CustomerDTO;
import shop.dto.OrderDTO;
import shop.dto.ProductDTO;

import java.net.URI;

@SpringBootApplication
public class WebShopClientApplication implements CommandLineRunner {
    @Autowired
    private RestOperations restTemplate;

    @Value("${webshop.service.customer.url}")
    private String customerServiceUrl;

    @Value("${webshop.service.order.url}")
    private String orderServiceUrl;

    @Value("${webshop.service.product.url}")
    private String productServiceUrl;

    @Value("${webshop.service.shoppingcart.url}")
    private String shoppincacrtServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(WebShopClientApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Gson gson = new Gson(); //Google json <-> obj
        ObjectMapper objectMapper = new ObjectMapper(); //jackson mapper
///////////////////////////////////////////////////////////////////////////////////////////////////
        //create customer
        CustomerDTO customerDto = new CustomerDTO("101", "Frank", "Brown", "fBrown@Hotmail.com", "123456");
        AddressDTO addressDTO = new AddressDTO("1000 N main Street", "Fairfield", "52557", "USA");
        customerDto.setAddress(addressDTO);

        //todo: call the customer component to add the customer
        ResponseEntity<?> responseEntity =
                doExchangeRest(customerDto, URI.create(customerServiceUrl + "/customer"), HttpMethod.POST);
        String jsonString = (String) responseEntity.getBody();
        CustomerDTO customerDTO = gson.fromJson(jsonString, CustomerDTO.class);
        System.out.println("Using Google Gson customerDTO = " + customerDTO);

        customerDTO = objectMapper.readValue(jsonString, CustomerDTO.class);
        System.out.println("Using Jackson mapper customerDTO = " + customerDTO);

        // get customer
        //todo: call the customer component to get the customer with id 101 and print the result
        responseEntity =
                doExchangeRest(null, URI.create(customerServiceUrl + "/customer/"
                        + customerDTO.getCustomerNumber()), HttpMethod.GET);
        jsonString = (String) responseEntity.getBody();
        customerDTO = objectMapper.readValue(jsonString, CustomerDTO.class);
        System.out.println("Get Customer using Rest exchange Get method = " + customerDTO);

////////////////////////////////////////////////////////////////////////////////////////////////
        //create products
        //todo: call the product component to create the first product
        doExchangeRest(null, URI.create(productServiceUrl + "/product/201/Dell%20Laptop/799.99"), HttpMethod.POST);

        //todo: call the product component to create the second product
        doExchangeRest(null, URI.create(productServiceUrl + "/product/202/HP%20Laptop/699.99"), HttpMethod.POST);

        //set stock
        //todo: call the product component to set the stock for the first product
        doExchangeRest(null, URI.create(productServiceUrl + "/product/stock/201/55/Fairfield,IA"), HttpMethod.POST);

        //get a product
        //todo: call the product component to get the the first product and print the result
        responseEntity = doExchangeRest(null, URI.create(productServiceUrl + "/product/201"), HttpMethod.GET);
        ProductDTO productDTO1 = objectMapper.readValue((String) responseEntity.getBody(), ProductDTO.class);
        responseEntity = doExchangeRest(null, URI.create(productServiceUrl + "/product/202"), HttpMethod.GET);
        ProductDTO productDTO2 = objectMapper.readValue((String) responseEntity.getBody(), ProductDTO.class);

/////////////////////////////////////////////////////////////////////////////////////////////////////////
        // add products to the shoppingcart
        //todo: call the shopping component to add the first product to the cart
        doExchangeRest(null, URI.create(shoppincacrtServiceUrl + "/cart/301/201/2"), HttpMethod.POST);

        //todo: call the shopping component to add the second product to the cart
        doExchangeRest(null, URI.create(shoppincacrtServiceUrl + "/cart/301/202/2"), HttpMethod.POST);

        //get the shoppingcart
        //todo: call the shopping component to get the cart and print the result
        doExchangeRest(null, URI.create(shoppincacrtServiceUrl + "/cart/301"), HttpMethod.GET);

        //checkout the cart
        //todo: call the shopping component to checkout the cart
        ResponseEntity<String> responseEntity1 =
                doExchangeRest(null, URI.create(shoppincacrtServiceUrl + "/cart/checkout/301"), HttpMethod.POST);
        String orderId = responseEntity1.getBody();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //get the order
        //todo: call the order component to get the order and print the result
        responseEntity =
                doExchangeRest(null, URI.create(orderServiceUrl + "/order/" + orderId), HttpMethod.GET);
        OrderDTO orderDTO = objectMapper.readValue((String) responseEntity.getBody(), OrderDTO.class);
        if (orderDTO != null)
            orderDTO.print();

        //add customer to order
        //todo: call the order component to add a customer to the order
        doExchangeRest(null, URI.create(orderServiceUrl + "/order/setCustomer/"+orderId+"/101"), HttpMethod.POST);

        //confirm the order
        //todo: call the order component to confirm the order
        doExchangeRest(null, URI.create(orderServiceUrl + "/order/"+orderId), HttpMethod.POST);

        //get the order
        //todo: call the order component to get the order and print the result
        doExchangeRest(null, URI.create(orderServiceUrl + "/order/"+orderId), HttpMethod.GET);
        OrderDTO order = restTemplate.getForObject(orderServiceUrl + "/order/"+orderId, OrderDTO.class);
        assert order != null;
        order.print();
    }

    private ResponseEntity<String> doExchangeRest(Object object, URI url, HttpMethod method) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> requestEntity = new HttpEntity<>(object, requestHeaders);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
            System.out.println("\n====================in doExchangeRest method=====================================================================================");
            System.out.println("Requested HTTP URL: " + url + " Method: " + method);
            System.out.println("RequestBody: " + object);
            System.out.println("ResponseEntity status: " + responseEntity.getStatusCode());
            System.out.println("ResponseEntity body: " + responseEntity.getBody());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------\n");

        } catch (Exception e) {
            System.out.println(e);
        }
        return responseEntity;
    }


}
