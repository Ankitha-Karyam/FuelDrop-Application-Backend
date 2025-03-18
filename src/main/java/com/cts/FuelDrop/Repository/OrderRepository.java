package com.cts.FuelDrop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.FuelDrop.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query("SELECT o FROM Order o WHERE o.phoneno = :phoneno")
    List<Order> ListOrdersByPhoneno(String phoneno);
	
	@Query("SELECT o FROM Order o WHERE o.orderStatus = :OrderStatus" )
	List<Order> OrdersByStatus(String OrderStatus);
	
	@Modifying
	@Query("UPDATE Order o SET o.orderStatus = :orderStatus WHERE o.orderId = :orderid")
	 void updateStatus(int orderid,String orderStatus);
	
	
}
