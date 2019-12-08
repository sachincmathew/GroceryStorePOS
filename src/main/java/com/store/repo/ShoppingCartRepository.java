package com.store.repo;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.store.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	List<ShoppingCart> findByStatusAndCloseDateAfter(String status, Date d);

}
