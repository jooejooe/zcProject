package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

public class AppLoginInfo {
	
	@ApiModelProperty("app登录名")
	private String logName;
	
	@ApiModelProperty("app登录密码")
	private String password;
	
	@ApiModelProperty("app用户id")
	private String userId;
	
	@ApiModelProperty("app用户新密码")
	private String newPassword;
	
	@ApiModelProperty("用户类型【1-工作人员，0-普通用户】")
	private String userType;
	
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
