package com.shopworld.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopworld.entities.Cart;


@Service
public interface CartService {

	public Cart saveCart(Integer productId,Integer userId);
	
	public List<Cart> getCartsByUser(Integer userId);
	
	public Integer getCountCart(Integer userId);

	public void updateQuantity(String sy, Integer cid);
}
