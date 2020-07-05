package greet.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "order")
@Data
public class Order {

	@Id
	private Integer orderId;

	private Shoppingcart shoppingcart;

	private Double subTotalPrice;

	public Order(Integer orderId, Shoppingcart shoppingcart) {

		this.orderId = orderId;
		this.shoppingcart = shoppingcart;
		doCalcSubTotalPrice(orderId, shoppingcart);
	}

	public void doCalcSubTotalPrice(Integer orderId, Shoppingcart shoppingcart) {
		if (shoppingcart == null)
			return;

		List<CartLine> cartLines = shoppingcart.getCarLines();
		if (subTotalPrice == null)
			subTotalPrice = 0.0;
		cartLines.stream().forEach(f -> {
			subTotalPrice += f.getProduct().getPrice() * f.getQuantity();
		});
	}

}
