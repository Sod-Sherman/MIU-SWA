package greet.service;

import java.util.List;

import greet.model.Product;

public interface ProductCatalogService {

	public void addProduct(Integer productnumber, String description, double price);

	public Product getProduct(Integer productnumber);

	public void setStock(int prodId, int quantity, String locationcode);

	public void addProduct(Integer productnumber, String description, double price, int quantity, String locationcode);

	List<Product> getAll();

	Product addProduct(Product product);
	
	void updateProduct(Product product);
}
