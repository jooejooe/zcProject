package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

public class PqInfos {
	@ApiModelProperty("工作人员姓名")
	private String WorkerName;
	
	@ApiModelProperty("排期开始时间")
	private String pqstart;
	
	@ApiModelProperty("排期结束时间")
	private String pqend;
	
	@ApiModelProperty("案件类别")
	private String title;
	
	@ApiModelProperty("申请方姓名")
	private String nameA;
	
	@ApiModelProperty("被申请方姓名")
	private String nameB;

	public String getWorkerName() {
		return WorkerName;
	}

	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}

	public String getPqstart() {
		return pqstart;
	}

	public void setPqstart(String pqstart) {
		this.pqstart = pqstart;
	}

	public String getPqend() {
		return pqend;
	}

	public void setPqend(String pqend) {
		this.pqend = pqend;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getNameA() {
		return nameA;
	}

	public void setNameA(String nameA) {
		this.nameA = nameA;
	}

	public String getNameB() {
		return nameB;
	}

	public void setNameB(String nameB) {
		this.nameB = nameB;
	}
}
