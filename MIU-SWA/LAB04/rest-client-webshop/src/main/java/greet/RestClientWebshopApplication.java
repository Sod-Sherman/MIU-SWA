package greet;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import greet.model.Product;
import greet.model.Stock;

@SpringBootApplication
public class RestClientWebshopApplication implements CommandLineRunner {
	@Autowired
	private RestOperations restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RestClientWebshopApplication.class, args);
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
		Product product1 = new Product(301, "HP Laptop", 699.0, new Stock(10, "Fairfield, IA"));
		Product product2 = new Product(302, "Dell Laptop", 799.0, new Stock(10, "Fairfield, IA"));
// adding the products
		URI url = new URI("http://localhost:8080/product/add");
		System.out.println("Adding the products...");
		System.out.println("Receiving message:" + doExchangeRest(product1, url, HttpMethod.POST).getBody());
		System.out.println("Receiving message:" + doExchangeRest(product2, url, HttpMethod.POST).getBody());
		System.out.println();
// adding the products to shopping cart
		url = new URI("http://localhost:8080/cart/add/1/301/3");
		System.out.println("Adding the product 301 to shopping cart...");
		System.out.println("Receiving message:" + doExchangeRest(null, url, HttpMethod.GET).getBody());
		System.out.println();

		url = new URI("http://localhost:8080/cart/add/1/302/2");
		System.out.println("Adding the product 302 to shopping cart...");
		System.out.println("Receiving message:" + doExchangeRest(null, url, HttpMethod.GET).getBody());
		System.out.println();

// create order for the shopping cart
		url = new URI("http://localhost:8080/order/create/1/1");
		System.out.println("Creating the cart ...");
		System.out.println("Receiving message:" + doExchangeRest(null, url, HttpMethod.GET).getBody());
		System.out.println();

	}

	private ResponseEntity<?> doExchangeRest(Object object, URI url, HttpMethod method) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<?> requestEntity = new HttpEntity<>(object, requestHeaders);

		ResponseEntity<?> responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
		return responseEntity;
	}

}
