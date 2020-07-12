package greet.webshopmongoorderplaceddd;

import greet.webshopmongoorderplaceddd.domain.order.Order;
import greet.webshopmongoorderplaceddd.domain.product.Product;
import greet.webshopmongoorderplaceddd.domain.shoppingcart.Shoppingcart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestOperations;

@SpringBootApplication
public class WebshopMongoOrderplaceDddApplication implements CommandLineRunner {
    @Autowired
    private RestOperations restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WebshopMongoOrderplaceDddApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //create products
        restTemplate.postForLocation("http://localhost:8080/product/103/HP Laptop/650.00", null);
        restTemplate.postForLocation("http://localhost:8080/product/104/Dell Laptop/775.00", null);

        //set stock
        restTemplate.postForLocation("http://localhost:8080/product/stock/103/33/Fairfield-IA", null);

        //get a product
        Product product = restTemplate.getForObject("http://localhost:8080/product/103", Product.class);
        System.out.println("-----Product-------\n" + product);

        // add products to the shoppingcart
        restTemplate.postForLocation("http://localhost:8080/cart/1/103/3", null);
        restTemplate.postForLocation("http://localhost:8080/cart/1/104/2", null);

        //get the shoppingcart
        Shoppingcart cart = restTemplate.getForObject("http://localhost:8080/cart/1", Shoppingcart.class);
        System.out.println("-----Shoppingcart-------\n" + cart);

        //checkout the cart
        restTemplate.postForLocation("http://localhost:8080/cart/checkout/1", null);

        //get the order
        Order order = restTemplate.getForObject("http://localhost:8080/order/1", Order.class);
        System.out.println("-----Order-------\n" + order);
    }
}
