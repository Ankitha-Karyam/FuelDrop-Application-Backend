package com.cts.FuelDrop.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.FuelDrop.Entity.Order;
import com.cts.FuelDrop.Exception.OrderNotFoundException;
import com.cts.FuelDrop.Repository.OrderRepository;
import com.cts.FuelDrop.Service.OrderService;

import config.Constants;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    @Override
    public Order deleteOrder(int orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
        return order;
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

   
    public double caluclatePrice(double quantity, double price) {
    	return quantity*price;
    }

	@Override
	public List<Order> getAllOrdersByPhoneno(String phonone) {
		return orderRepository.ListOrdersByPhoneno(phonone);
	}
	
	@Override
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}

	@Override
	public List<Order> ordersByStatus(String OrderStatus) {
		return orderRepository.OrdersByStatus(OrderStatus);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateOrder(int orderId, String orderStatus) {
		orderRepository.updateStatus(orderId, orderStatus);
	}
	
	
}
