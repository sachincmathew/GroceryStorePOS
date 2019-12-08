package com.store.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.CartItem;
import com.store.model.Inventory;
import com.store.model.ShoppingCart;
import com.store.model.ShoppingCartItems;
import com.store.repo.InventoryRepository;
import com.store.repo.ShoppingCartItemRepository;
import com.store.repo.ShoppingCartRepository;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	private static final String OPEN = "OPEN";
	// private static final String CLOSED = "CLOSED";

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private ShoppingCartItemRepository shoppingCartItemRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@RequestMapping(path = "/findAll", method = RequestMethod.GET)
	public @ResponseBody Iterable<ShoppingCart> getAllItems() {
		return shoppingCartRepository.findAll();
	}

	@RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
	public @ResponseBody ShoppingCart findCart(@PathVariable Integer id) {
		Optional<ShoppingCart> i;
		try {
			i = shoppingCartRepository.findById(id);
		} catch (Exception e) {
			return null;// TODO: Exception Handling
		}
		if (i.isPresent())
			return i.get();
		return null;
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public @ResponseBody String createCart() {
		ShoppingCart sc = new ShoppingCart();
		sc.setStatus(OPEN);
		sc.setOpenDate(new Date());
		try {
			sc = shoppingCartRepository.save(sc);
		} catch (Exception e) {
			return "An error occurred. Please contact the Administrator.";
		}
		return sc.getId().toString();
	}

	@RequestMapping(path = "/addItemToCart", method = RequestMethod.POST)
	public @ResponseBody String AddItemToCart(@RequestBody CartItem ci) {
		try {
			ShoppingCartItems sci = new ShoppingCartItems();
			List<ShoppingCartItems> itemsInCart = shoppingCartItemRepository.findByCartIdAndItemId(ci.cartId,
					ci.itemId);
			if (itemsInCart.size() == 0) {
				ShoppingCart sc = shoppingCartRepository.findById(ci.getCartId()).get();
				Inventory i = inventoryRepository.findById(ci.getItemId()).get();
				sci.setCart(sc);
				sci.setItem(i);
				sci.setQuantity(ci.getQuantity());
			} else {
				sci = itemsInCart.get(0);
				sci.setQuantity(sci.getQuantity() + ci.getQuantity());
			}
			sci = shoppingCartItemRepository.save(sci);
		} catch (Exception e) {
			return "An error occurred. Please contact the Administrator.";
		}
		return "item added";
	}

	@RequestMapping(path = "/removeItemFromCart", method = RequestMethod.POST)
	public @ResponseBody String RemoveItemFromCart(@RequestBody CartItem ci) {
		try {
			List<ShoppingCartItems> items = shoppingCartItemRepository.findByCartIdAndItemId(ci.getCartId(),
					ci.getItemId());
			for (Iterator<ShoppingCartItems> iterator = items.iterator(); iterator.hasNext();) {
				ShoppingCartItems sci = (ShoppingCartItems) iterator.next();
				if (sci.getQuantity() - ci.getQuantity() <= 0) {
					shoppingCartItemRepository.deleteById(sci.getId());
				} else {
					sci.setQuantity(sci.getQuantity() - ci.getQuantity());
					shoppingCartItemRepository.save(sci);
				}
			}
		} catch (Exception e) {
			return "An error occurred. Please contact the Administrator.";
		}
		return "item removed";
	}
}
