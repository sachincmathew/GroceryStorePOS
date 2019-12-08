package com.store.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.CheckoutItemsHistory;
import com.store.model.ShoppingCart;
import com.store.repo.CheckoutItemsHistoryRepository;
import com.store.repo.ShoppingCartRepository;

@RestController
@RequestMapping("/reports")
public class ReportsController {
	private static final String CLOSED = "CLOSED";

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private CheckoutItemsHistoryRepository checkoutItemsRepository;

	@RequestMapping(path = "/calculateProfit/{days}", method = RequestMethod.GET)
	public Float calculateProfit(@PathVariable int days) {
		LocalDate localDate = LocalDate.now().minusDays(days);
		Date fromDate = java.sql.Date.valueOf(localDate);

		List<ShoppingCart> lsc = shoppingCartRepository.findByStatusAndCloseDateAfter(CLOSED, fromDate);
		System.out.println(lsc.size());

		List<Integer> cartIds = new ArrayList<Integer>();
		for (ShoppingCart sc : lsc) {
			cartIds.add(sc.getId());
		}

		List<CheckoutItemsHistory> cih = checkoutItemsRepository.findByCartIdIn(cartIds);
		System.out.println(cih.size());
		
		float profit = 0.0F;
		for(CheckoutItemsHistory c: cih) {
			profit += (c.getRetailPrice() - c.getPurchasePrice()) * c.getQuantity();
		}
		return profit;
	}
}
