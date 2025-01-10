package com.shopworld.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopworld.entities.ProductOrder;


public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{

	List<ProductOrder> findByUserId(Integer userId);

	ProductOrder findByOrderId(String orderId);


}
