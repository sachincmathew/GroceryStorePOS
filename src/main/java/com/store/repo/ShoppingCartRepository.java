package com.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

}
