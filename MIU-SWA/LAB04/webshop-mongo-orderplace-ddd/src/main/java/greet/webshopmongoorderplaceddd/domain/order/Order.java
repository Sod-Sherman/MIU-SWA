package greet.webshopmongoorderplaceddd.domain.order;

import greet.webshopmongoorderplaceddd.domain.shoppingcart.CartLine;
import greet.webshopmongoorderplaceddd.domain.shoppingcart.Shoppingcart;
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
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private Integer orderId;
    private List<OrderLine> orderLineList;
    private OrderStatus orderStatus;

    public void addShoppingcart(Shoppingcart shoppingcart) {
        if (orderLineList == null) orderLineList = new ArrayList<>();

        shoppingcart.getCartLines()
                .forEach(cartLine -> {

                    Product product = Product.builder()
                            .productId(cartLine.getProduct().getProductId())
                            .description(cartLine.getProduct().getDescription())
                            .price(cartLine.getProduct().getPrice())
                            .build();

                    updateQuantity(product, cartLine.getQuantity());
                });

    }

    OrderLine findOrderLineInOrderLinesByProduct(Product product) {
        return orderLineList.stream()
                .filter(p -> p.getProduct().getProductId().equals(product.getProductId()))
                .findFirst()
                .orElse(null);
    }

    void updateQuantity(Product product, Integer quantity) {
        OrderLine orderLine = findOrderLineInOrderLinesByProduct(product);
       if(orderLine != null) {
           orderLineList.remove(orderLine);
           orderLine.setQuantity(orderLine.getQuantity() + quantity);
       } else {
           orderLine = new OrderLine(product, quantity);
       }
       orderLineList.add(orderLine);
    }

}
