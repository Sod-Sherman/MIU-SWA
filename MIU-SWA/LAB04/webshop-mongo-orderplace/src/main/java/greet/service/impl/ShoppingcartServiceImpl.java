package greet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import greet.model.CartLine;
import greet.model.Product;
import greet.model.Shoppingcart;
import greet.model.Stock;
import greet.repository.ShoppingcartRepository;
import greet.service.ProductCatalogService;
import greet.service.ShoppingcartService;

@Service
public class ShoppingcartServiceImpl implements ShoppingcartService {

	@Autowired
	private ProductCatalogService productCatalogService;

	@Autowired
	private ShoppingcartRepository shoppingcartRepository;

	@Override
	public List<Shoppingcart> getCart() {
		return shoppingcartRepository.findAll();

	}

	@Override
	public Shoppingcart addToCart(Integer shoppingcartId, Integer productId, int quantity) {

		Product product = productCatalogService.getProduct(productId);
		Stock stock = product.getStock();

		if (stock.getQuantity() < quantity)
			return null;
//		Place to order
//		stock.setQuantity(stock.getQuantity() - quantity);
//		product.setStock(stock);

		List<Shoppingcart> shoppingcartList = shoppingcartRepository.findAll();
		Shoppingcart shoppingcart = shoppingcartList.stream().filter(s -> s.getShoppingcartId() == shoppingcartId)
				.findAny().orElse(new Shoppingcart());

		List<CartLine> cartLineList = shoppingcart.getCarLines();
		if (cartLineList == null)
			cartLineList = new ArrayList<>();

		CartLine cartLine = cartLineList.stream().filter(c -> c.getProduct().getProductId() == productId).findAny()
				.orElse(new CartLine());

		cartLineList.remove(cartLine);

		cartLine.setProduct(product);
		cartLine.setQuantity(quantity + cartLine.getQuantity());
		if (cartLine.getQuantity() > stock.getQuantity())
			return null;

		cartLineList.add(cartLine);

		shoppingcart.setCarLines(cartLineList);
		if (shoppingcart.getShoppingcartId() == null)
			shoppingcart.setShoppingcartId(shoppingcartId);

		shoppingcartRepository.save(shoppingcart);

		return shoppingcart;

	}

	@Override
	public boolean checkOut(Integer shoppingcartId) {
		Shoppingcart shoppingcart = getShoppingcart(shoppingcartId);
		shoppingcart.getCarLines().stream().forEach(f -> {
			
			f.getProduct().getStock().setQuantity(f.getProduct().getStock().getQuantity() - f.getQuantity());
			productCatalogService.updateProduct(f.getProduct());

		});
		shoppingcartRepository.save(shoppingcart);
		return false;
	}

	@Override
	public Shoppingcart getShoppingcart(Integer shoppingcartId) {
		return shoppingcartRepository.findById(shoppingcartId).get();
	}

	@Override
	public void delete(Shoppingcart shoppingcart) {
		shoppingcartRepository.delete(shoppingcart);
		
	}

}
