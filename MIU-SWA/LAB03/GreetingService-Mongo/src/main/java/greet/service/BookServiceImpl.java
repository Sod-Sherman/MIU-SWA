package greet.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import greet.model.Book;
import greet.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(String isbn) {
		bookRepository.deleteById(isbn);

	}

	@Override
	public Book getBook(String isbn) {
		return bookRepository.findById(isbn).get();
	}

	@Override
	public Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getBooksBetweenPrice(double priceLow, double priceHigh, int page, int size) {
		List<Book> returnBooks = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, size);
		Page<Book> booksPaged = bookRepository.findByPriceBetween(priceLow, priceHigh, pageableRequest);
		returnBooks = booksPaged.getContent();
		return returnBooks;
	}

}
