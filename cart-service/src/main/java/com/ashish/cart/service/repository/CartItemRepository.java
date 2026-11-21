package com.ashish.cart.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashish.cart.service.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	Optional<CartItem> findByUserIdAndId(Integer userId ,Long id);
	
	List<CartItem> findByUserId (Integer userId);
	
	void deleteByUserId(Integer userId);
	
	void deleteByUserIdAndId(Integer userId ,Long id);

}
