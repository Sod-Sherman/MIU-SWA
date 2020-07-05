package greet.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderId;

	private Shoppingcart shoppingcart;

	private Double subTotalPrice;

}
