package com.storage.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.storage.domain.Goods;
import com.storage.pagination.Pagination;
import com.storage.service.GoodsService;

@SuppressWarnings("unchecked")
public class GoodsAction extends BaseAction<Goods> {

	private static final long serialVersionUID = 1L;

	private GoodsService service;

	public void setService(GoodsService service) {
		this.service = service;
	}

	public String findGoodsAjax() {
		Goods goods = service.findGoods(model);
		this.pushToValueStackRoot(goods);
		return "json";
	}

	public String saveGoods() {
		service.saveGoods(model);
		return "success_save";
	}

	public String findByNameLikeAjax() {
		List<Goods> list = service.findByNameLike(model);
		this.pushToValueStackRoot(list);
		return "json";
	}

	public String listPage() {
		Pagination<Goods> pagination = new Pagination<Goods>();
		Map<String, String[]> map = ServletActionContext.getRequest()
				.getParameterMap();
		pagination.setParameterMap(map);
		if (pageNum > 0) {
			pagination.setPageNum(pageNum);
		}
		if (pageSize > 0) {
			pagination.setPageSize(pageSize);
		}

		service.findListPage(pagination);
		result = pagination;
		return "success_listPage";
	}
}
