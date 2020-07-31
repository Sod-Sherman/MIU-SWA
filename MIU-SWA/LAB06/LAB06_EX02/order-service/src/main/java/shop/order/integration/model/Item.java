package shop.order.integration.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Item.class)
public class Item {
    private String productId;
    private Integer quantity;

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}
