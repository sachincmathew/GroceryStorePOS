package com.store.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.model.Inventory;
import com.store.repo.InventoryRepository;

@Service
public class InverntoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	public String addNewItem(Inventory i) {
		try {
			i = inventoryRepository.save(i);
		} catch (Exception e) {
			if (e.getCause().getCause().getMessage().indexOf("Duplicate entry") >= 0) {
				return i.getName() + " already exists in the inventory.";
			} else {
				return "An error occurred. Please contact the Administrator.";
			}
		}
		return i.getId().toString();
	}

	public String updateItem(Inventory i) {
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

	public String removeItem(Integer id) {
		try {
			inventoryRepository.deleteById(id);
		} catch (Exception e) {
			if (e.getMessage().indexOf("No class com.store.model.Inventory entity with id") >= 0)
				return "no record found";
			return "An error occurred. Please contact the Administrator.";
		}
		return "removed";
	}

	public Inventory findItem(Integer id) {
		Optional<Inventory> i;
		try {
			i = inventoryRepository.findById(id);
		} catch (Exception e) {
			return null;// TODO: Exception Handling
		}
		if (i.isPresent())
			return i.get();
		return null;
	}

	public Iterable<Inventory> getAllItems() {
		return inventoryRepository.findAll();
	}
}
