package com.store.model.custom;

import java.util.Date;
import java.util.Set;

public class ShoppingCartDetails {
	private Integer id;

	private String status;

	private Date openDate;

	private Date closeDate;

	private Set<Item> cartItems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Item> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<Item> cartItems) {
		this.cartItems = cartItems;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

}
