package com.xzx.viewModel;

import java.util.List;

import com.xzx.model.Access;
import com.xzx.model.Subscribe;

import io.swagger.annotations.ApiModelProperty;

public class SubscribeInfos {
	@ApiModelProperty("预约相关信息")
	private Subscribe subscribe;
	
	@ApiModelProperty("预约上传相关附件")
	private List<Access> listAccess;
	
	public Subscribe getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
	public List<Access> getListAccess() {
		return listAccess;
	}
	public void setListAccess(List<Access> listAccess) {
		this.listAccess = listAccess;
	}
}
