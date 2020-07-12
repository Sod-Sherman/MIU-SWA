package greet.webshopmongoorderplaceddd.service.impl;

import greet.webshopmongoorderplaceddd.domain.order.Order;
import greet.webshopmongoorderplaceddd.domain.order.OrderStatus;
import greet.webshopmongoorderplaceddd.domain.shoppingcart.Shoppingcart;
import greet.webshopmongoorderplaceddd.repository.OrderRepositoty;
import greet.webshopmongoorderplaceddd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepositoty orderRepositoty;

    @Override
    public Order getOrder(Integer orderId) {
        return orderRepositoty.findById(orderId).orElse(null);
    }

    @Override
    public void createOrder(Shoppingcart shoppingcart) {

        Order order = orderRepositoty.findById(shoppingcart.getShoppingcartId()).orElseGet(() ->
                Order.builder()
                .orderId(shoppingcart.getShoppingcartId())
                .orderStatus(OrderStatus.PENDING)
                .build());
        order.addShoppingcart(shoppingcart);
        orderRepositoty.save(order);
        System.out.println("order saved");

    }
}
