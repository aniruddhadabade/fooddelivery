package com.foodie.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodie.delivery.entity.Payment;

public interface PayementRepo extends JpaRepository<Payment,Long>{

}
