package com.storage.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.storage.dao.GenericDao;
import com.storage.pagination.Pagination;

public class BaseService<T> {
	protected GenericDao<T> dao;

	public void setDao(GenericDao<T> dao) {
		this.dao = dao;
	}

	public String getCurrentDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	public String findParameterByName(String name, Pagination pagination) {
		Map<String, String[]> map = pagination.getParameterMap();
		return map.get(name) == null ? null : map.get(name)[0];
	}
}
