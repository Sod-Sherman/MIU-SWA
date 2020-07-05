package greet;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import greet.model.Product;
import greet.model.Stock;
import greet.repository.primary.ProductRepository;

@SpringBootApplication
public class WebshopMongoApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebshopMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Product> products = Arrays.asList(
				new Product(new Integer(101), "iPhone X", 999.0, new Stock(10, "Axel A")),
				new Product(new Integer(102), "Android 10", 799.0, new Stock(10, "Axel B")),
				new Product(new Integer(103), "Google GX", 899.0, new Stock(10, "Axel C"))
				);
		System.out.println("starting...");
		productRepository.save(products);
		System.out.println("Done");
		
	}

}
