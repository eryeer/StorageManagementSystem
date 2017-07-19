package com.storage.web.action;

import com.storage.domain.UserInfo;
import com.storage.service.UserInfoService;
import com.storage.utils.ServletUtils;

public class UserInfoAction extends BaseAction<UserInfo> {

	private static final long serialVersionUID = 1L;
	private UserInfoService service;

	public void setService(UserInfoService service) {
		this.service = service;
	}

	public String login() {
		UserInfo userInfo = service.findUserInfo(model);
		if (userInfo != null) {
			ServletUtils.userLoginToSession(userInfo);
			return SUCCESS;
		} else {
			this.addActionError(this.getText("UserInfo.loginError"));
			return "login_error";
		}
	}

}
