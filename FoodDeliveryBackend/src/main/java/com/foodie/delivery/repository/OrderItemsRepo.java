package com.foodie.delivery.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodie.delivery.entity.OrderItems;
import com.foodie.delivery.entity.OrdersData;

public interface OrderItemsRepo extends JpaRepository<OrderItems,Long>{
	List<OrderItems> findByOrderdata(OrdersData orderdata);
}
