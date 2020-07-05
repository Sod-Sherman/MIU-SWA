package greet.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartLine implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Product product;
	private int quantity;

}
