package greet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import greet.model.Product;
import greet.model.Stock;
import greet.repository.primary.ProductRepository;

@Service@Transactional
public class ProductCatalogServiceImpl implements greet.service.ProductCatalogService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(Integer productnumber, String description, double price) {
		
		addProduct(productnumber, description, price, -1, null);
	
	}
	
	@Override
	public void addProduct(Integer productnumber, String description, double price, int quantity, String locationcode) {

		Stock stock = new Stock(quantity, locationcode);
		Product product = productRepository.exists(productnumber) ? productRepository.findOne(productnumber)
				: new Product();

		if(description != null) product.setDescription(description);
		if(price != -1) product.setPrice(price);
		if (product.getStock() != null || quantity != -1) {
			stock.setQuantity(product.getStock().getQuantity() + quantity);
			if(locationcode != null) stock.setLocationcode(locationcode);
		}

		product.setStock(stock);

		productRepository.save(product);

	}

	@Override
	public Product getProduct(Integer productnumber) {
		return productRepository.findOne(productnumber);
	}

	@Override
	public void setStock(int prodId, int quantity, String locationcode) {
		
		Product product = getProduct(prodId);
		addProduct(prodId, null, -1, quantity, locationcode);

	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

}
