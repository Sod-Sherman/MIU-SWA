package shop.order.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Order {
	@Id
	private String ordernumber = String.valueOf(System.currentTimeMillis());
	private Date date;
	private OrderStatus status;
	private Customer customer;

	private ArrayList<OrderLine> orderlineList = new ArrayList<>();

	public Order() {
	}

	public Order(Date date, OrderStatus status) {
		this.date = date;
		this.status = status;
	}


	private double getTotalPrice() {
		double totalPrice = 0.0;
		for (OrderLine oline : orderlineList) {
			totalPrice=totalPrice+(oline.getProduct().getPrice() * oline.getQuantity());
		}
		return totalPrice;
	}
	
	public void addOrderLine(OrderLine oline) {
		orderlineList.add(oline);		
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public ArrayList<OrderLine> getOrderlineList() {
		return orderlineList;
	}

	public void setOrderlineList(ArrayList<OrderLine> orderlineList) {
		this.orderlineList = orderlineList;
	}

	public void confirm() {
		status=OrderStatus.DELIVERED;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
