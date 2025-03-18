package com.cts.FuelDrop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cts.FuelDrop.Entity.Order;
import com.cts.FuelDrop.Service.Impl.OrderServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;
	
	@GetMapping("/ListOrdersofUsers/{phoneno}")
	public ResponseEntity<List<Order>> ListAllOrdersByPhoneno(@PathVariable("phoneno") String Phoneno){
		List<Order> order = orderService.getAllOrdersByPhoneno(Phoneno);
		return ResponseEntity.ok(order);	
	}
	
	@GetMapping("/allOrders")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/ordersbyStatus/{orderStatus}")
	public ResponseEntity<List<Order>> orderByStatus(@PathVariable("orderStatus")String orderStatus){
		List<Order> orders = orderService.ordersByStatus(orderStatus);
		return ResponseEntity.ok(orders);
	}
	
	@PutMapping("/updateOrder/{orderId}")
	public void updateOrderStatus(@PathVariable int orderId, @RequestBody String orderStatus){
	    orderService.updateOrder(orderId, orderStatus);

	}

	@GetMapping("/orderid/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) {
		Order order = orderService.getOrderById(id);
		return ResponseEntity.ok(order);
	}

	@DeleteMapping("orderid/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("id") int id) {
		Order order = orderService.deleteOrder(id);
		return ResponseEntity.ok(order);
	}

	
	@PostMapping("/fuelOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		Order newOrder = orderService.addOrder(order);
		return ResponseEntity.ok(newOrder);
	}
	

}