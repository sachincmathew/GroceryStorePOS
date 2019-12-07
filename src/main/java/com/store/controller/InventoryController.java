package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.Inventory;
import com.store.repo.InventoryRepository;

@RestController
@RequestMapping(path = "/inventory")
public class InventoryController {

	@Autowired
	private InventoryRepository inventoryRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String addNewItem(@RequestBody Inventory i) {
		Inventory newI;
		try {
			newI = inventoryRepository.save(i);
		} catch (Exception e) {
			if (e.getCause().getCause().getMessage().indexOf("Duplicate entry") >= 0) {
				return i.getName() + " already exists in the inventory.";
			} else {
				return "An error occurred. Please contact the Administrator.";
			}
		}
		return newI.getId().toString();
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Inventory> getAllItems() {
		return inventoryRepository.findAll();
	}
}
