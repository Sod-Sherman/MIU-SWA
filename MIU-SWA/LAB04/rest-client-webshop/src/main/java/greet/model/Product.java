package greet.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer productId;

	private String description;

	private double price;

	private Stock stock;
}
