package com.storage.service;

import java.util.List;

import com.storage.domain.Store;

public interface StoreService {

	void save(Store model);

	List<Store> findAll();

	Store findById(Store model);

	void update(Store model);

	void delete(Store model);

}
