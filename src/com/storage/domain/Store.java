package com.storage.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */

public class Store implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String addr;
	private String manager;
	private Set goodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Store() {
	}

	/** full constructor */
	public Store(String name, String addr, String manager, Set goodses) {
		this.name = name;
		this.addr = addr;
		this.manager = manager;
		this.goodses = goodses;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", addr=" + addr
				+ ", manager=" + manager + ", goodses=" + goodses + "]";
	}

}