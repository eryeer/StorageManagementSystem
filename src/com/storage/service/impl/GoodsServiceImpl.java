package com.storage.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.storage.constants.Constants;
import com.storage.domain.Goods;
import com.storage.domain.History;
import com.storage.domain.UserInfo;
import com.storage.pagination.Pagination;
import com.storage.service.GoodsService;
import com.storage.utils.ServletUtils;

@SuppressWarnings("unchecked")
public class GoodsServiceImpl extends BaseService<Goods> implements
		GoodsService {

	@Override
	public Goods findGoods(Goods model) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);
		criteria.add(Restrictions.eq("nm", model.getNm()));
		List<Goods> list = dao.findByCriteria(criteria);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void saveGoods(Goods model) {
		History history = new History();
		UserInfo userInfo = ServletUtils.getLoginUserFromSession();
		history.setUser(userInfo.getName());
		history.setType(Constants.History.IN_STROE);
		history.setDatetime(getCurrentDateString());
		if (StringUtils.isNotBlank(model.getId())) {
			Goods goods = dao.findById(Goods.class, model.getId());
			goods.setAmount(goods.getAmount() + model.getAmount());
			history.setAmount(model.getAmount());
			history.setRemain(goods.getAmount());
			goods.getHistories().add(history);

		} else {
			history.setAmount(model.getAmount());
			history.setRemain(model.getAmount());
			model.getHistories().add(history);
			dao.save(model);

		}

	}

	@Override
	public List<Goods> findByNameLike(Goods model) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);
		criteria.add(Restrictions.like("name", "%" + model.getName() + "%"));
		List<Goods> list = dao.findByCriteria(criteria);
		return list.isEmpty() ? null : list;
	}

	@Override
	public void findListPage(Pagination<Goods> pagination) {
		String nm = findParameterByName("nm", pagination);
		String name = findParameterByName("name", pagination);
		String storeId = findParameterByName("store.id", pagination);

		DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);
		if (StringUtils.isNotBlank(nm)) {
			criteria.add(Restrictions.eq("nm", nm));
		}
		if (StringUtils.isNotBlank(name)) {
			criteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (StringUtils.isNotBlank(storeId)) {
			criteria.add(Restrictions.eq("store.id", storeId));
		}

		long totalCount = dao.findCountByCriteria(criteria);

		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		List<Goods> list = dao.findListPageByCriteria(criteria,
				pagination.getBeginCount(), pagination.getPageSize());

		pagination.setTotalCount(totalCount);
		pagination.setList(list);
	}
}
