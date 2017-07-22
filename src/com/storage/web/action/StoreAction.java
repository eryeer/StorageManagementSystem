package com.storage.web.action;

import java.util.List;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.storage.domain.Store;
import com.storage.service.StoreService;

public class StoreAction extends BaseAction<Store> {
	private static final long serialVersionUID = 1L;
	private StoreService service;

	public void setService(StoreService service) {
		this.service = service;
	}

	public String add() {
		service.save(model);
		return "success";
	}

	public String findAll() {
		List<Store> list = service.findAll();
		this.putToValueStackMap("list", list);
		return "success_findAll";
	}

	public String preEdit() {
		Store store = service.findById(model);
		this.pushToValueStackRoot(store);
		return "success_preEdit";
	}

	@InputConfig(resultName = "input_edit")
	public String edit() {
		service.update(model);
		return "success";
	}

	public String delete() {
		service.delete(model);
		return "success";
	}

	public String findAllAjax() {
		List<Store> list = service.findAll();
		this.pushToValueStackRoot(list);
		return "json";
	}
}
