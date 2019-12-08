package com.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@OneToMany(mappedBy = "item")
	private Integer id;

	@Column(unique = true)
	private String name;

	private Float purchasePrice;

	private Float retailPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Float getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Float retailPrice) {
		this.retailPrice = retailPrice;
	}

}
