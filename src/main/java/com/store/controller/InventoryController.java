package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.store.model.Inventory;
import com.store.service.InverntoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Inventory API(s)" })
@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {

	@Autowired
	private InverntoryService inventoryService;

	@ApiOperation(value = "Add new item to the inventory")
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public @ResponseBody String addNewItem(@RequestBody Inventory item) {
		if (item.getName() == null || item.getName().isEmpty() || item.getPurchasePrice() == null
				|| item.getRetailPrice() == null) {
			return "incorrect request format (missing attribute)";
		}
		return inventoryService.addNewItem(item);
	}

	@ApiOperation(value = "Update the **purchase price** and **retail price** of a item.")
	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	public @ResponseBody String updateItem(@RequestBody Inventory item) {
		if (item.getId() == null || item.getName() == null || item.getName().isEmpty()
				|| item.getPurchasePrice() == null || item.getRetailPrice() == null) {
			return "incorrect request format (missing attribute)";
		}
		return inventoryService.updateItem(item);
	}

	@ApiOperation(value = "Remove an item from the inventory. Any OPEN shopping cart that contains the deleted item will be updated automatically.")
	@RequestMapping(path = "/remove/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String removeItem(@PathVariable Integer itemId) {
		return inventoryService.removeItem(itemId);
	}

	@ApiOperation(value = "Retrieve the details of a item in the inventory")
	@RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
	public @ResponseBody Inventory findItem(@PathVariable Integer itemId) {
		return inventoryService.findItem(itemId);
	}

	@ApiOperation(value = "List all items in the inventory")
	@RequestMapping(path = "/findAll", method = RequestMethod.GET)
	public @ResponseBody Iterable<Inventory> getAllItems() {
		return inventoryService.getAllItems();
	}
}
