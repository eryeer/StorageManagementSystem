package com.storage.pagination;

import java.util.List;
import java.util.Map;

public class Pagination<T> {
	private int pageNum = 1;
	private int pageSize = 3;
	private Map<String, String[]> parameterMap;

	private long totalCount;
	private List<T> list;

	private int pageCount;
	private int[] pageBar;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		pageCount = (int) ((totalCount + pageSize - 1) / pageSize);
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @return the beginCount
	 */
	public int getBeginCount() {
		return (pageNum - 1) * pageSize;
	}

	/**
	 * @return the pageBar
	 */

	public int[] getPageBar() {
		int size;// 页码条数字数量
		int beginPage;// 页码条第一数字
		int endPage;// 页码条最后一个数字
		pageCount = getPageCount();
		if (pageCount < 10) {
			size = pageCount;
			beginPage = 1;
			endPage = pageCount;
		} else {
			size = 10;
			beginPage = pageNum - 5;
			endPage = pageNum + 4;
			if (beginPage < 1) {
				beginPage = 1;
				endPage = 10;
			} else if (endPage > pageCount) {
				endPage = pageCount;
				beginPage = endPage - 9;
			}
		}
		pageBar = new int[size];
		for (int i = 0; i < pageBar.length; i++) {
			pageBar[i] = beginPage + i;
		}
		return pageBar;
	}

	/**
	 * @return the previousPage
	 */
	public int getPreviousPage() {
		return pageNum <= 1 ? pageNum : (pageNum - 1);
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return pageNum >= getPageCount() ? pageNum : (pageNum + 1);
	}

	/**
	 * @return the firstPage
	 */
	public int getFirstPage() {
		return 1;
	}

	/**
	 * @return the lastPage
	 */
	public int getLastPage() {
		return getPageCount();
	}

	/**
	 * 返回请求时需要携带的搜索条件参数
	 * 
	 * @return parameterStr
	 */
	public String getParameterStr() {
		StringBuffer parameterStr = new StringBuffer();
		for (String key : parameterMap.keySet()) {
			if (!key.equals("pageSize") && !key.equals("pageNum")) {
				String[] values = parameterMap.get(key);
				if (values != null && values.length > 0) {
					parameterStr.append("&").append(key).append("=")
							.append(values[0]);
				}
			}
		}
		return parameterStr.toString();
	}
}
