package com.store.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.model.CheckoutItemsHistory;
import com.store.model.ShoppingCart;
import com.store.repo.CheckoutItemsHistoryRepository;
import com.store.repo.ShoppingCartRepository;

@Service
public class ReportService {

	private static final String CLOSED = "CLOSED";

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private CheckoutItemsHistoryRepository checkoutItemsRepository;

	public Float calculateProfit(int days) {
		LocalDate localDate = LocalDate.now().minusDays(days);
		Date fromDate = java.sql.Date.valueOf(localDate);

		List<ShoppingCart> lsc = shoppingCartRepository.findByStatusAndCloseDateAfter(CLOSED, fromDate);

		List<Integer> cartIds = new ArrayList<Integer>();
		for (ShoppingCart sc : lsc) {
			cartIds.add(sc.getId());
		}

		List<CheckoutItemsHistory> cih = checkoutItemsRepository.findByCartIdIn(cartIds);

		float profit = 0.0F;
		for (CheckoutItemsHistory c : cih) {
			profit += (c.getRetailPrice() - c.getPurchasePrice()) * c.getQuantity();
		}
		return profit;
	}

}
