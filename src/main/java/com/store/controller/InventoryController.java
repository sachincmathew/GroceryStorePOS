package com.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

	@RequestMapping("/inventory")
	public String inventory() {
		return "inventory";
	}
}
