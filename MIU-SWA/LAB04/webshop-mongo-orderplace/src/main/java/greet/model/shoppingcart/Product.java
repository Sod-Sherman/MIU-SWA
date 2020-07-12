package greet.model.shoppingcart;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productInShoppingCart")
@Data
public class Product {

    @Id
    private Integer productId;
    private double price;
    private String desc;

}
