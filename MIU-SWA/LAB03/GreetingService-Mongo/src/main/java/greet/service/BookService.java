package greet.service;


import java.util.List;


import greet.model.Book;

public interface BookService {
	
	public void addBook(Book book);
	public void deleteBook(String isbn);
	public Book getBook(String isbn);
	public Iterable<Book> getAllBooks();
	public List<Book> getBooksBetweenPrice(double priceLow, double priceHigh, int page, int size);

}
