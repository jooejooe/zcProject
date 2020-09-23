package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

public class TJLZInfo {
	@ApiModelProperty("当前进度id")
	private String speedId;
	
	@ApiModelProperty("当前进度状态")
	private String speedState;
	
	@ApiModelProperty("仲裁案件id")
	private String caseId;
	
	@ApiModelProperty("仲裁流转原因")
	private String tjlzReason;
	
	public String getSpeedId() {
		return speedId;
	}
	public void setSpeedId(String speedId) {
		this.speedId = speedId;
	}
	public String getSpeedState() {
		return speedState;
	}
	public void setSpeedState(String speedState) {
		this.speedState = speedState;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getTjlzReason() {
		return tjlzReason;
	}
	public void setTjlzReason(String tjlzReason) {
		this.tjlzReason = tjlzReason;
	}
}
