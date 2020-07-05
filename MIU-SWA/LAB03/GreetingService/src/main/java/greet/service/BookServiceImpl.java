package greet.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import greet.model.Book;
import greet.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public void addBook(Book book) {
		bookRepository.addBook(book);
	}

	@Override
	public void deleteBook(String isbn) {
		bookRepository.deleteBookByIsbn(isbn);

	}

	@Override
	public Book getBook(String isbn) {
		return bookRepository.getBookByIsbn(isbn);
	}

	@Override
	public Set<Book> getAllBooks() {
		return bookRepository.getBooks();
	}

}
