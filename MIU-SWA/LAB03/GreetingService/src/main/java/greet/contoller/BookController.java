package greet.contoller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import greet.model.Book;
import greet.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public ResponseEntity<Set<Book>> getBooks() {
		return new ResponseEntity<Set<Book>>(bookService.getAllBooks(), HttpStatus.OK);
	}
	
	@PostMapping("/book")
	public void addBook(@RequestBody Book book) {
		bookService.addBook(book);
	}
	
	@DeleteMapping("/book/{isbn}")
	public void deleteBook(@PathVariable("isbn") String isbn) {
		bookService.deleteBook(isbn);
	}
	
	
}
