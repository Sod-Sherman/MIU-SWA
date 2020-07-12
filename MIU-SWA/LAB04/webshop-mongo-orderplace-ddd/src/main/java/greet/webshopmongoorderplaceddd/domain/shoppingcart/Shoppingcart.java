package greet.webshopmongoorderplaceddd.domain.shoppingcart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shoppingcart {

    @Id
    private Integer shoppingcartId;
    private List<CartLine> cartLines;

    public void addToCartLine(Product product, Integer quantity) {
        if (cartLines == null) cartLines = new ArrayList<>();
        CartLine cartLine = new CartLine(product, quantity);
        for (CartLine c : cartLines) {
            if (c.getProduct().equals(product)) {
                cartLine = c;
                cartLine.setQuantity(c.getQuantity() + quantity);
                cartLines.remove(c);
            }
        }
        cartLines.add(cartLine);
    }
}
