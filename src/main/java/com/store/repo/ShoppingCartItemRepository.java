package com.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.ShoppingCartItems;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItems, Integer> {

}
