package shop.order.integration.model;

import lombok.*;

import java.util.List;

@Data@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QueueItem {
    private String queueItemId;
    private List<Item> items;
}
