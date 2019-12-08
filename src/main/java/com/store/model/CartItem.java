package com.store.model;

public class CartItem {
	public int cartId;

	public CartItem() {
	}

	public CartItem(int cartId, int itemId, int quantity) {
		super();
		this.cartId = cartId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public int itemId;

	public int quantity;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}