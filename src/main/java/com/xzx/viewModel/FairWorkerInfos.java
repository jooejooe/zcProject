package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

public class FairWorkerInfos {
	@ApiModelProperty("机构名称")
	private String DepartmentName;

	@ApiModelProperty("工作人员id")
	private String FairWorkerId;

	@ApiModelProperty("工作人员姓名")
	private String WorkerName;

	@ApiModelProperty("手机号码")
	private String Telephone;

	@ApiModelProperty("头像")
	private String Image;

	@ApiModelProperty("业务专长")
	private String Speciality;

	@ApiModelProperty("性别")
	private String sexName;

	@ApiModelProperty("学历")
	private String Education;

	@ApiModelProperty("专业")
	private String profession;

	@ApiModelProperty("所属区域")
	private String RegionName;

	@ApiModelProperty("分数")
	private String evaluateScore;

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getRegionName() {
		return RegionName;
	}

	public void setRegionName(String regionName) {
		RegionName = regionName;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getFairWorkerId() {
		return FairWorkerId;
	}

	public void setFairWorkerId(String fairWorkerId) {
		FairWorkerId = fairWorkerId;
	}

	public String getWorkerName() {
		return WorkerName;
	}

	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getSpeciality() {
		return Speciality;
	}

	public void setSpeciality(String speciality) {
		Speciality = speciality;
	}

	public String getEvaluateScore() {
		return evaluateScore;
	}

	public void setEvaluateScore(String evaluateScore) {
		this.evaluateScore = evaluateScore;
	}
}
