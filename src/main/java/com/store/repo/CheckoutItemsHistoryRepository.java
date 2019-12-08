package com.store.repo;

import org.springframework.data.repository.CrudRepository;

import com.store.model.CheckoutItemsHistory;

public interface CheckoutItemsHistoryRepository extends CrudRepository<CheckoutItemsHistory, Integer> {

}
