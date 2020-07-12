package greet.webshopmongoorderplaceddd.service;

import greet.webshopmongoorderplaceddd.domain.order.Order;
import greet.webshopmongoorderplaceddd.domain.shoppingcart.Shoppingcart;

public interface OrderService {
    Order getOrder(Integer orderId);
    void createOrder(Shoppingcart shoppingcart);
}
