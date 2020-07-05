package greet.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Shoppingcart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer shoppingcartId;

	private List<CartLine> carLines;

}
