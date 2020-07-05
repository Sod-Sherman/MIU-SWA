package greet.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import greet.model.Book;
import greet.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired 
	private EntityLinks links;
	
	@GetMapping("/books")
	public ResponseEntity<Iterable<Book>> getBooks() {
		return new ResponseEntity<Iterable<Book>>(bookService.getAllBooks(), HttpStatus.OK);
	}
	
	@PostMapping("/book")
	public void addBook(@RequestBody Book book) {
		bookService.addBook(book);
	}
	
	@DeleteMapping("/book/{isbn}")
	public void deleteBook(@PathVariable("isbn") String isbn) {
		bookService.deleteBook(isbn);
	}
	
	@GetMapping(value = "/book/price", produces = MediaType.APPLICATION_JSON_VALUE) //?low=10.0&high=100.0&page=1&size=3
	public ResponseEntity <List<Book>> getBooksByPriceRangePaged(
			@RequestParam(value = "low", defaultValue = "10") double lowPrice,
			@RequestParam(value = "high", defaultValue = "100") double highPrice,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "2") int limit) {
		//List<Book> returnBooks = new ArrayList<>();
		List<Book> books = bookService.getBooksBetweenPrice(lowPrice, highPrice, page, limit);
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	
	}
	
	
}
