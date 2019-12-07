package com.store.repo;

import org.springframework.data.repository.CrudRepository;

import com.store.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

}
