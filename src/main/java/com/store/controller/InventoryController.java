package com.store.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.Inventory;
import com.store.repo.InventoryRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Inventory API(s)"})
@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {

	@Autowired
	private InventoryRepository inventoryRepository;

	@ApiOperation(value = "Add new item to the inventory")
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewItem(@RequestBody Inventory i) {
		if (i.getName() == null || i.getName().isEmpty() || i.getPurchasePrice() == null
				|| i.getRetailPrice() == null) {
			return "incorrect request format (missing attribute)";
		}
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

	@ApiOperation(value = "Update the **purchase price** and **retail price** of a item.")
	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	public @ResponseBody String updateItem(@RequestBody Inventory i) {
		if (i.getId() == null || i.getName() == null || i.getName().isEmpty() || i.getPurchasePrice() == null
				|| i.getRetailPrice() == null) {
			return "incorrect request format (missing attribute)";
		}
		try {
			Optional<Inventory> optionalI = inventoryRepository.findById(i.getId());
			if (optionalI.isPresent()) {
				Inventory existingI = new Inventory();
				existingI.setName(i.getName());
				existingI.setPurchasePrice(i.getPurchasePrice());
				existingI.setRetailPrice(i.getRetailPrice());
				inventoryRepository.save(existingI);
			} else {
				return "record not found";
			}
		} catch (Exception e) {
			return "An error occurred. Please contact the Administrator.";
		}
		return "updated";
	}

	@ApiOperation(value = "Remove an item from the inventory. Any OPEN shopping cart that contains the deleted item will be updated automatically.")
	@RequestMapping(path = "/remove/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String removeItem(@PathVariable Integer id) {
		try {
			inventoryRepository.deleteById(id);
		} catch (Exception e) {
			if (e.getMessage().indexOf("No class com.store.model.Inventory entity with id") >= 0)
				return "no record found";
			return "An error occurred. Please contact the Administrator.";
		}
		return "removed";
	}

	@ApiOperation(value = "Retrieve the details of a item in the inventory")
	@RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
	public @ResponseBody Inventory findItem(@PathVariable Integer id) {
		Optional<Inventory> i;
		try {
			i = inventoryRepository.findById(id);
		} catch (Exception e) {
			return null;//TODO: Exception Handling
		}
		if(i.isPresent())
			return i.get();
		return null;
	}

	@ApiOperation(value = "List all items in the inventory")
	@RequestMapping(path = "/findAll", method = RequestMethod.GET)
	public @ResponseBody Iterable<Inventory> getAllItems() {
		return inventoryRepository.findAll();
	}
}
