package greet.service;

import java.util.Set;

import greet.model.Book;

public interface BookService {
	
	public void addBook(Book book);
	public void deleteBook(String isbn);
	public Book getBook(String isbn);
	public Set<Book> getAllBooks();

}
