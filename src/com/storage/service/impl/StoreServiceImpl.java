package com.storage.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.storage.domain.Store;
import com.storage.service.StoreService;

public class StoreServiceImpl extends BaseService<Store> implements
		StoreService {
	@CacheEvict(value = "myCache", allEntries = true)
	@Override
	public void save(Store model) {
		dao.save(model);
	}

	@Cacheable("myCache")
	@Override
	public List<Store> findAll() {
		return dao.findAll(Store.class);
	}

	@Override
	public Store findById(Store model) {
		return dao.findById(Store.class, model.getId());
	}

	@CacheEvict(value = "myCache", allEntries = true)
	@Override
	public void update(Store model) {
		dao.update(model);
	}

	@CacheEvict(value = "myCache", allEntries = true)
	@Override
	public void delete(Store model) {
		dao.delete(model);
	}

}
