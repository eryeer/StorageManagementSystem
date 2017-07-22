package com.storage.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.storage.domain.UserInfo;
import com.storage.service.UserInfoService;

public class UserInfoServiceIml extends BaseService<UserInfo> implements
		UserInfoService {

	@Override
	public UserInfo findUserInfo(UserInfo userInfo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserInfo.class);
		criteria.add(Restrictions.eq("name", userInfo.getName()));
		criteria.add(Restrictions.eq("password", userInfo.getPassword()));
		List<UserInfo> list = dao.findByCriteria(criteria);
		return (list == null || list.isEmpty()) ? null : list.get(0);
	}

}
