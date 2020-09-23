package com.xzx.viewModel;

import java.util.List;

import com.xzx.model.Access;

import io.swagger.annotations.ApiModelProperty;

public class AddAccessInfos {
	@ApiModelProperty("补充附件材料")
	private List<Access> listAccess;

	@ApiModelProperty("业务id")
	private String businessId;

	@ApiModelProperty("业务类型")
	private String modelType;

	public List<Access> getListAccess() {
		return listAccess;
	}

	public void setListAccess(List<Access> listAccess) {
		this.listAccess = listAccess;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
}
