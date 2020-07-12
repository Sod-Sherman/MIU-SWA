package greet.webshopmongoorderplaceddd.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document@Data@Builder@NoArgsConstructor@AllArgsConstructor
public class Stock {
    private Integer quantity;
    private String location;

}
