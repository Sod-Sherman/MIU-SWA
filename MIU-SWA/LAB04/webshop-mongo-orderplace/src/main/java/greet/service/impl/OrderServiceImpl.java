package greet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import greet.model.Order;
import greet.model.Shoppingcart;
import greet.repository.OrderRepository;
import greet.service.OrderService;
import greet.service.ShoppingcartService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ShoppingcartService shoppingcartService;

	@Override
	public Order createOrder(Integer orderId, Shoppingcart shoppingcart) {
		Order order = new Order(orderId, shoppingcart);
		shoppingcartService.delete(shoppingcart);
		return orderRepository.save(order);
	}

	@Override
	public Order getOrder(Integer orderId) {
		return orderRepository.findById(orderId).get();
	}

}
