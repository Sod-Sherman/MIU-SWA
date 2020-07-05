package greet.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import greet.model.Book;

@Component
public class BookRepository {
	
	private Set<Book> books;
	
	public BookRepository() {
		if(books == null) this.books = new HashSet<>();
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	
	public void deleteBookByIsbn(String isbn) {
		books.remove(
				books.stream()
				.filter(b -> b.getIsbn().equals(isbn))
				.findFirst().get());
	}
	
	public Book getBookByIsbn(String isbn) {
		return books.stream()
				.filter(b -> b.getIsbn().equals(isbn))
				.findFirst()
				.get();
	}
	

}
