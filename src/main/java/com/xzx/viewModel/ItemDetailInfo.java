package com.xzx.viewModel;

public class ItemDetailInfo {
	/**
	 * 申请人姓名
	 */
	private String RealName;
	
	/**
	 * 公务员姓名
	 */
	private String WorkerName;
	
	/**
	 * 公务员电话
	 */
	private String WorkerTelephone;
	
	/**
	 * 受理机构
	 */
	private String DepartmentName;
	
	/**
	 * 申办时间
	 */
	private String CreateDate;
	
	/**
	 * 使用地
	 */
	private String usePlaceName;
	
	/**
	 * 业务类型
	 */
	private String BusinessType;
	
	/**
	 * 上门服务(0 不上门 1 上门)
	 */
	private Integer IsDoor;
	
	/**
	 * 工作人员头像
	 */
	private String fairworkerImg;
	
	/**
	 * 申请人身份证号
	 */
	private String ApplySFZH;
	
	/**
	 * 申请人手机号
	 */
	private String ApplyPhone;
	
	/**
	 * 申办机构地点
	 */
	private String DepartmentAddress;
	
	/**
	 * 机构电话
	 */
	private String DepartmentPhone;
	
	/**
	 * 业务id
	 */
	private String BusinessId;
	
	/**
	 * 备注
	 */
	private String Context;

	/**
	 * 法律援助申请人类型
	 */
	private String applyType;
	
	/**
	 * 公证办理业务类型id
	 */
	private int BiddingMattersId;
	
	private String evaluateTypeStr;

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String realName) {
		RealName = realName;
	}

	public String getWorkerName() {
		return WorkerName;
	}

	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}

	public String getWorkerTelephone() {
		return WorkerTelephone;
	}

	public void setWorkerTelephone(String workerTelephone) {
		WorkerTelephone = workerTelephone;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getUsePlaceName() {
		return usePlaceName;
	}

	public void setUsePlaceName(String usePlaceName) {
		this.usePlaceName = usePlaceName;
	}

	public String getBusinessType() {
		return BusinessType;
	}

	public void setBusinessType(String businessType) {
		BusinessType = businessType;
	}

	public Integer getIsDoor() {
		return IsDoor;
	}

	public void setIsDoor(Integer isDoor) {
		IsDoor = isDoor;
	}
	
	public String getFairworkerImg() {
		return fairworkerImg;
	}

	public void setFairworkerImg(String fairworkerImg) {
		this.fairworkerImg = fairworkerImg;
	}

	public String getApplySFZH() {
		return ApplySFZH;
	}

	public void setApplySFZH(String applySFZH) {
		ApplySFZH = applySFZH;
	}

	public String getApplyPhone() {
		return ApplyPhone;
	}

	public void setApplyPhone(String applyPhone) {
		ApplyPhone = applyPhone;
	}
	
	public String getDepartmentAddress() {
		return DepartmentAddress;
	}

	public void setDepartmentAddress(String departmentAddress) {
		DepartmentAddress = departmentAddress;
	}
	
	public String getDepartmentPhone() {
		return DepartmentPhone;
	}

	public void setDepartmentPhone(String departmentPhone) {
		DepartmentPhone = departmentPhone;
	}

	public String getBusinessId() {
		return BusinessId;
	}

	public void setBusinessId(String businessId) {
		BusinessId = businessId;
	}
	
	public String getContext() {
		return Context;
	}

	public void setContext(String context) {
		Context = context;
	}
	

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	

	public int getBiddingMattersId() {
		return BiddingMattersId;
	}

	public void setBiddingMattersId(int biddingMattersId) {
		BiddingMattersId = biddingMattersId;
	}
	
	public String getEvaluateTypeStr() {
		return evaluateTypeStr;
	}

	public void setEvaluateTypeStr(String evaluateTypeStr) {
		this.evaluateTypeStr = evaluateTypeStr;
	}
}
