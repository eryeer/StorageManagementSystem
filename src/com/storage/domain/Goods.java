package com.storage.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private String id;
	private Store store;
	private String name;
	private String nm;
	private String unit;
	private Double amount;
	private Set histories = new HashSet(0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(Store store, String name, String nm, String unit,
			Double amount, Set histories) {
		this.store = store;
		this.name = name;
		this.nm = nm;
		this.unit = unit;
		this.amount = amount;
		this.histories = histories;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNm() {
		return this.nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@JSON(serialize = false)
	public Set getHistories() {
		return this.histories;
	}

	public void setHistories(Set histories) {
		this.histories = histories;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", store=" + store + ", name=" + name
				+ ", nm=" + nm + ", unit=" + unit + ", amount=" + amount
				+ ", histories=" + histories + "]";
	}

	// 用于jQuery ui 的下拉联想显示
	public String getLabel() {
		return this.name + "(" + this.store.getName() + ")";
	}

	public String getValue() {
		return this.name;
	}

}