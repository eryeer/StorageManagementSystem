package com.storage.service.impl;

import java.util.List;

import com.storage.dao.GenericDao;
import com.storage.domain.Store;
import com.storage.service.StoreService;

public class StoreServiceImpl extends BaseService implements StoreService {
	private GenericDao<Store> dao;

	public void setDao(GenericDao<Store> dao) {
		this.dao = dao;
	}

	@Override
	public void save(Store model) {
		dao.save(model);
	}

	@Override
	public List<Store> findAll() {
		return dao.findAll(Store.class);
	}

	@Override
	public Store findById(Store model) {
		return dao.findById(Store.class, model.getId());
	}

	@Override
	public void update(Store model) {
		dao.update(model);
	}

	@Override
	public void delete(Store model) {
		dao.delete(model);
	}

}
