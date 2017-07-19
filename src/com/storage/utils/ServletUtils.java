package com.storage.utils;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
public class ServletUtils {

	public static final String LOGIN_USER = "login_user";

	public static void userLoginToSession(Object loginUser) {
		ActionContext.getContext().getSession().put(LOGIN_USER, loginUser);
	}

	public static void userLogoutFromSession() {
		ActionContext.getContext().getSession().put(LOGIN_USER, null);
	}

	public static <T> T getLoginUserFromSession() {
		return (T) ActionContext.getContext().getSession().get(LOGIN_USER);

	}

}
