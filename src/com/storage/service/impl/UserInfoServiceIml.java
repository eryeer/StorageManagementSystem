package com.storage.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.storage.dao.GenericDao;
import com.storage.domain.UserInfo;
import com.storage.service.UserInfoService;

public class UserInfoServiceIml extends BaseService implements UserInfoService {
	private GenericDao<UserInfo> dao;

	public void setDao(GenericDao<UserInfo> dao) {
		this.dao = dao;
	}

	@Override
	public UserInfo findUserInfo(UserInfo userInfo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserInfo.class);
		criteria.add(Restrictions.eq("name", userInfo.getName()));
		criteria.add(Restrictions.eq("password", userInfo.getPassword()));
		List<UserInfo> list = dao.findByCriteria(criteria);
		return (list == null || list.isEmpty()) ? null : list.get(0);
	}

}
