package edu.miu.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/")
    public String sendOrderTest() {
        orderService.send("ORDER #99887766 request has sent by Kafka to Payment service by topic ORDER_CREATED_EVENT");
        return "Order is requested. Check kafka message";
    }
}
