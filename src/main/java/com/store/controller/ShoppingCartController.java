package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.ShoppingCart;
import com.store.model.custom.CartItem;
import com.store.model.custom.ShoppingCartDetails;
import com.store.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Shopping Cart API(s)" })
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@ApiOperation(value = "Lists all shopping carts")
	@RequestMapping(path = "/findAll", method = RequestMethod.GET)
	public @ResponseBody Iterable<ShoppingCartDetails> getAllItems() {
		// TODO: Checked out cart's details needs to be formed
		return shoppingCartService.getAllItems();
	}

	@ApiOperation(value = "Retrieve the details of a shopping cart")
	@RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
	public @ResponseBody ShoppingCart findCart(@PathVariable Integer id) {
		return shoppingCartService.findCart(id);
	}

	@ApiOperation(value = "Add new item to the inventory")
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public @ResponseBody String createCart() {
		return shoppingCartService.createCart();
	}

	@ApiOperation(value = "Add an item and the added quantity to a shopping cart.")
	@RequestMapping(path = "/addItemToCart", method = RequestMethod.POST)
	public @ResponseBody String AddItemToCart(@RequestBody CartItem ci) {
		// TODO: Validations
		return shoppingCartService.AddItemToCart(ci);
	}

	@ApiOperation(value = "Remove specified number of items from the shopping cart.")
	@RequestMapping(path = "/removeItemFromCart", method = RequestMethod.DELETE)
	public @ResponseBody String RemoveItemFromCart(@RequestBody CartItem ci) {
		// TODO: Validations
		return shoppingCartService.RemoveItemFromCart(ci);
	}

	@ApiOperation(value = "Checkout an open shopping cart and set it's status to CLOSED. It returns the total price that a consumer is expected to pay.")
	@RequestMapping(path = "/checkout/{id}", method = RequestMethod.GET)
	public @ResponseBody Float checkout(@PathVariable Integer id) {
		return shoppingCartService.checkout(id);
	}

}
