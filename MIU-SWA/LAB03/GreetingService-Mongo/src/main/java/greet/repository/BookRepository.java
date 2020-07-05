package greet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import greet.model.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, String>{
	Page<Book> findByPriceBetween(double priceLow, double priceHigh, Pageable pageable); 
}
