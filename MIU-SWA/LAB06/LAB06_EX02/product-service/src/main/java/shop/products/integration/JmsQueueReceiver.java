package shop.products.integration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import shop.order.integration.model.QueueItem;
import shop.products.service.ProductCatalogService;

@Component
@Log4j2
public class JmsQueueReceiver {

    @Autowired
    private ProductCatalogService productCatalogService;

    @JmsListener(destination = "${webshop.service.jms_queue.confirm_notification}")
    public void receiveMessage(final QueueItem queueItem) {
        log.info("JMS receiver received message: " + queueItem);
        productCatalogService.updateStockQuantity(queueItem);
    }

}
