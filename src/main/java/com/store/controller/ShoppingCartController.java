package com.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {

	@RequestMapping("/cart")
	public String cart() {
		return "cart";
	}
}
