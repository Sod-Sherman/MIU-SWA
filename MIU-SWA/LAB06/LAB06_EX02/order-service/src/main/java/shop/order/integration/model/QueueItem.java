package shop.order.integration.model;


import com.google.gson.Gson;
import lombok.Getter;
import shop.order.domain.Order;

import java.util.ArrayList;
import java.util.List;


@Getter
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = QueueItem.class)
public class QueueItem {
    private final String queueItemId;
    private final List<Item> items;

    public QueueItem(Order order) {
        this.queueItemId = order.getOrdernumber();
        this.items = new ArrayList<>();
        order.getOrderlineList().forEach(
                o -> {
                    String key = o.getProduct().getProductnumber();
                    Integer value = o.getQuantity();
                    items.add(new Item(key, value));
                }
        );
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
