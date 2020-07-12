package greet.webshopmongoorderplaceddd.controller;

import greet.webshopmongoorderplaceddd.domain.order.Order;
import greet.webshopmongoorderplaceddd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getCart(@PathVariable Integer orderId) {
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
