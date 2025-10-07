package com.foodie.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.delivery.entity.OrdersData;

@Repository
public interface OrdersDataRepo extends JpaRepository<OrdersData,Long>{
    List<OrdersData> findByRestaurant_RestaurentId(Long restaurentId);
    List<OrdersData> findByUserRegistration_Rid(Long rid);
}
