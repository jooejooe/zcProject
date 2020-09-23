package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

public class CaseAssignInfo {
	/**
	 * 案件（事项id）
	 */
	@ApiModelProperty("案件（事项id）")
	private String modelId;
	
	/**
	 * 指派的工作人员id
	 */
	@ApiModelProperty("指派的工作人员id")
	private String workerId;
	
	/**
	 * 事项类型(0 公证办理 1 人民仲裁 2 司法鉴定 3 法律援助 4 预约公证办理 5 预约人民仲裁 6 预约司法鉴定 7 预约法律援助 8 预约律师)
	 */
	@ApiModelProperty("事项类型(0 公证办理 1 人民仲裁 2 司法鉴定 3 法律援助 4 预约公证办理 5 预约人民仲裁 6 预约司法鉴定 7 预约法律援助 8 预约律师)")
	private String modelType;

	@ApiModelProperty("当前进度id")
	private String speedId;
	
	@ApiModelProperty("操作指派的工作人员（主任）id")
	private String zprId;

	public String getZprId() {
		return zprId;
	}

	public void setZprId(String zprId) {
		this.zprId = zprId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	
	
	public String getSpeedId() {
		return speedId;
	}

	public void setSpeedId(String speedId) {
		this.speedId = speedId;
	}
}
