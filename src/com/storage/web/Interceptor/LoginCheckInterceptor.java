package com.storage.web.Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.storage.domain.UserInfo;
import com.storage.utils.ServletUtils;

public class LoginCheckInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		UserInfo userInfo = (UserInfo) invocation.getInvocationContext()
				.getSession().get(ServletUtils.LOGIN_USER);
		if (userInfo == null) {
			ActionSupport action = (ActionSupport) invocation.getAction();
			action.addActionError("pls login");
			return "login";
		} else {
			String result = invocation.invoke();
			return result;
		}

	}

}
