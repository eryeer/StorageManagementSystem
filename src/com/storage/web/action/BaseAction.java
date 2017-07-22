package com.storage.web.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 1L;
	protected T model;
	/**
	 * 存入对象栈action中的result工具属性
	 */
	protected Object result;

	@Override
	public T getModel() {

		return model;
	}

	protected int pageNum;
	protected int pageSize;

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 构造方法中利用反射创建泛型T的实例
	 */
	public BaseAction() {
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
		Type[] types = parameterizedType.getActualTypeArguments();
		try {
			model = ((Class<T>) types[0]).newInstance();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 对象栈有名压栈
	 * 
	 * @param key
	 * @param value
	 */
	public void setToValueStackRoot(String key, Object value) {
		ActionContext.getContext().getValueStack().set(key, value);
	}

	/**
	 * 对象栈匿名压栈
	 * 
	 * @param obj
	 */
	public void pushToValueStackRoot(Object obj) {
		ActionContext.getContext().getValueStack().push(obj);
	}

	/**
	 * 上下文栈存入键值对
	 * 
	 * @param key
	 * @param value
	 */
	public void putToValueStackMap(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}

	/**
	 * result工具属性值的获取
	 * 
	 * @return result
	 */
	public Object getResult() {
		return result;
	}
}
