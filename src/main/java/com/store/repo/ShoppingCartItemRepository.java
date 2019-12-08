package com.store.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.ShoppingCartItems;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItems, Integer> {

	List<ShoppingCartItems> findByCartId(Integer cartId);

	List<ShoppingCartItems> findByCartIdAndItemId(Integer cartId, Integer itemId);

}
