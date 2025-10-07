package com.foodie.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodie.delivery.entity.UserRegistration;

public interface UserRegistrationRepo extends JpaRepository<UserRegistration,Long>{
	@Query("SELECT u FROM UserRegistration u WHERE u.userName = :userName")
	Optional<UserRegistration> findByUserName(@Param("userName") String userName);
	
}
