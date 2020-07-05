package greet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import greet.model.Book;
import greet.repository.BookRepository;

@SpringBootApplication
public class GreetingServiceApplication implements CommandLineRunner {
	
	@Autowired
	private BookRepository bookRepository;

	private static final Logger LOGGER = LogManager.getLogger(GreetingServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GreetingServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Adding Books..");
		bookRepository.addBook(new Book("123", "Book 1", 20.95, "James Brown"));
		bookRepository.addBook(new Book("124", "Book 2", 28.00, "Mary Jones"));
		
	}

}
