package com.xzx.viewModel;

import java.util.List;

import com.xzx.model.Access_bczj;

import io.swagger.annotations.ApiModelProperty;

public class OtherAccessInfos {
	@ApiModelProperty("调查取证相关证据")
	List<Access_bczj> listZJ;
	
	@ApiModelProperty("事项id")
	private String ModelId;
	
	@ApiModelProperty("事项类型")
	private String ModelType;

	public List<Access_bczj> getListZJ() {
		return listZJ;
	}

	public void setListZJ(List<Access_bczj> listZJ) {
		this.listZJ = listZJ;
	}
	
	public String getModelId() {
		return ModelId;
	}

	public void setModelId(String modelId) {
		ModelId = modelId;
	}

	public String getModelType() {
		return ModelType;
	}

	public void setModelType(String modelType) {
		ModelType = modelType;
	}

}
