package com.store.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String status;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
    private Set<ShoppingCartItems> cartItems;

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

}
