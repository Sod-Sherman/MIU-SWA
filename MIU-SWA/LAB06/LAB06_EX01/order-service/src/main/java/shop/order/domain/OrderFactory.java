package shop.order.domain;

import java.util.Date;

import shop.order.service.dto.ShoppingCartDTO;
import shop.order.service.dto.CartLineDTO;


public class OrderFactory {
	
	public static Order createOrder(ShoppingCartDTO cartDTO) {
		Order order = new Order(new Date(),OrderStatus.PLACED);
		for (CartLineDTO cline : cartDTO.getCartlineList()) {
			OrderLine oline = new OrderLine();
			//create an order product from a shopping product
			Product product = new Product(cline.getProduct().getProductnumber(), cline.getProduct().getDescription(), cline.getProduct().getPrice());
			oline.setProduct(product);
			oline.setQuantity(cline.getQuantity());
			order.addOrderLine(oline);
		}
		return order;
	}
}
