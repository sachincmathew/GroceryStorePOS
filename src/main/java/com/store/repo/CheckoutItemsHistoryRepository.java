package com.store.repo;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.store.model.CheckoutItemsHistory;

public interface CheckoutItemsHistoryRepository extends JpaRepository<CheckoutItemsHistory, Integer> {

	List<CheckoutItemsHistory> findByCartIdIn(Collection<Integer>  cartIds);

}
