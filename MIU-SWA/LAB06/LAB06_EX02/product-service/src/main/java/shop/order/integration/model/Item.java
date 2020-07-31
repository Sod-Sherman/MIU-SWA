package shop.order.integration.model;

import lombok.*;

@Data@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class Item {
    private String productId;
    private Integer quantity;
}
