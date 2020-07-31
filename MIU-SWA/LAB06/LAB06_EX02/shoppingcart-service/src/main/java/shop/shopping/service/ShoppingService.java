package shop.shopping.service;

import java.io.IOException;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.shopping.service.dto.ProductDTO;
import shop.shopping.service.dto.ShoppingCartAdapter;
import shop.shopping.service.dto.ShoppingCartDTO;
import shop.shopping.service.orderservice.OrderService;
import shop.shopping.service.productservice.ProductCatalogService;
import shop.shopping.domain.Product;
import shop.shopping.domain.ShoppingCart;
import shop.shopping.repository.ShoppingCartRepository;

@Service
@Log4j2
public class ShoppingService {
	@Autowired
	ProductCatalogService productCatalogService;
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	OrderService orderService;

	public void addToCart(String cartId, String productnumber, int quantity) throws IOException {
		ProductDTO productsproduct = productCatalogService.getProduct(productnumber);
		//create a shopping product from a products product
		Product product = new Product(productsproduct.getProductnumber(),productsproduct.getDescription(),productsproduct.getPrice());
		Optional<ShoppingCart> cartOptional = shoppingCartRepository.findById(cartId);
		if (cartOptional.isPresent() && product != null) {
			ShoppingCart cart = cartOptional.get();
			cart.addToCart(product, quantity);
			shoppingCartRepository.save(cart);
		}
		else if (product != null) {
			ShoppingCart cart = new ShoppingCart();
			cart.setCartid(cartId);
			cart.addToCart(product, quantity);
			shoppingCartRepository.save(cart);
		}		
	}
	
	public ShoppingCartDTO getCart(String cartId) {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		if (cart.isPresent())
		  return ShoppingCartAdapter.getShoppingCartDTO(cart.get());
		else
			return null;
	}

	public String checkout(String cartId) throws IOException {
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		if (cart.isPresent()) {
			return orderService.createOrder(ShoppingCartAdapter.getShoppingCartDTO(cart.get()));
		}
		log.warn("No such shopping cart: {}", cartId);
		return null;
	}
}
