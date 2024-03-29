package com.store.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.model.CheckoutItemsHistory;
import com.store.model.Inventory;
import com.store.model.ShoppingCart;
import com.store.model.ShoppingCartItems;
import com.store.model.custom.CartItem;
import com.store.model.custom.Item;
import com.store.model.custom.ShoppingCartDetails;
import com.store.repo.CheckoutItemsHistoryRepository;
import com.store.repo.InventoryRepository;
import com.store.repo.ShoppingCartItemRepository;
import com.store.repo.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	private static final String OPEN = "OPEN";
	private static final String CLOSED = "CLOSED";

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private ShoppingCartItemRepository shoppingCartItemRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private CheckoutItemsHistoryRepository checkoutRepository;

	private static HashMap<Integer, Inventory> inventoryMap;

	private void updateInventoryMap() {
		// TODO- fetch from distributed cache
		Iterable<Inventory> inventoryList = inventoryRepository.findAll();
		inventoryMap = new HashMap<>();
		for (Iterator<Inventory> iterator = inventoryList.iterator(); iterator.hasNext();) {
			Inventory i = iterator.next();
			inventoryMap.put(i.getId(), i);
		}
	}

	public Iterable<ShoppingCartDetails> getAllItems() {// TODO: Checked out cart's details needs to be formed
		updateInventoryMap();
		List<ShoppingCartDetails> lstScd = new ArrayList<>();
		Iterable<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
		for (Iterator<ShoppingCart> iterator = shoppingCarts.iterator(); iterator.hasNext();) {
			ShoppingCart sc = iterator.next();
			ShoppingCartDetails scd = new ShoppingCartDetails();
			scd.setId(sc.getId());
			scd.setStatus(sc.getStatus());
			scd.setOpenDate(sc.getOpenDate());
			scd.setCloseDate(sc.getCloseDate());
			HashSet<Item> items = new HashSet<>();

			List<ShoppingCartItems> itemsInCart = shoppingCartItemRepository.findByCartId(sc.getId());
			for (ShoppingCartItems sci : itemsInCart) {
				Item i = new Item();
				i.setItemId(sci.getItem().getId());
				i.setItemName(inventoryMap.get(sci.getItem().getId()).getName());
				i.setQuantity(sci.getQuantity());
				items.add(i);
			}
			scd.setCartItems(items);
			lstScd.add(scd);
		}
		return lstScd;
	}

	public ShoppingCart findCart(Integer id) {
		Optional<ShoppingCart> i;
		try {
			i = shoppingCartRepository.findById(id);
		} catch (Exception e) {
			return null;// TODO: Exception Handling
		}
		if (i.isPresent())
			return i.get();
		return null;// TODO: Total also needs to be calculated and displayed
	}

	public String createCart() {
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

	public String AddItemToCart(CartItem ci) {
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

	public String RemoveItemFromCart(CartItem ci) {
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

	public Float checkout(Integer id) {
		float total = 0.0F;
		Optional<ShoppingCart> osc;
		try {
			osc = shoppingCartRepository.findById(id);
		} catch (Exception e) {
			return null;// TODO: Exception Handling
		}

		if (osc.isPresent()) {
			ShoppingCart sc = osc.get();
			updateInventoryMap();

			List<ShoppingCartItems> itemsInCart = shoppingCartItemRepository.findByCartId(sc.getId());
			for (ShoppingCartItems sci : itemsInCart) {
				CheckoutItemsHistory c = new CheckoutItemsHistory();
				c.setCart(sc);
				c.setItemId(sci.getItem().getId());
				c.setItemName(inventoryMap.get(sci.getItem().getId()).getName());
				c.setQuantity(sci.getQuantity());
				c.setPurchasePrice(inventoryMap.get(sci.getItem().getId()).getPurchasePrice());
				c.setRetailPrice(inventoryMap.get(sci.getItem().getId()).getRetailPrice());
				total += c.getQuantity() * c.getRetailPrice();
				checkoutRepository.save(c);
				shoppingCartItemRepository.deleteById(sci.getId());
			}
			sc.setStatus(CLOSED);
			sc.setCloseDate(new Date());
			shoppingCartRepository.save(sc);
			return total;
		}
		return -1.0F;
	}

}
