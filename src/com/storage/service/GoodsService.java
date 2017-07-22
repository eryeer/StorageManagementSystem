package com.storage.service;

import java.util.List;

import com.storage.domain.Goods;
import com.storage.pagination.Pagination;

public interface GoodsService {

	Goods findGoods(Goods model);

	void saveGoods(Goods model);

	List<Goods> findByNameLike(Goods model);

	void findListPage(Pagination<Goods> pagination);

}
