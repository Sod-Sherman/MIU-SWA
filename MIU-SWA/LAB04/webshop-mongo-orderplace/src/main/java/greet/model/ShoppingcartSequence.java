package greet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "shoppingcartSequence")
@Data
public class ShoppingcartSequence {

	@Id
	private String id;
	private int seq;

}
