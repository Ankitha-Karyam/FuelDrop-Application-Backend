package com.cts.FuelDrop.Service;

import java.util.List;

import com.cts.FuelDrop.Entity.Order;

public interface OrderService {
	Order getOrderById(int orderId);

	Order deleteOrder(int orderId);


	Order addOrder(Order order);

	List<Order> getAllOrdersByPhoneno(String phonone);

	public List<Order> getAllOrders();
	
	List<Order> ordersByStatus(String OrderStatus);
	
	void updateOrder(int orderId,String orderStatus);
	
	
}
