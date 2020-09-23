package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

/**
 * 案件指派业务数据
 * @author Administrator
 *
 */
public class CaseAssignInfos {
	/**
	 * 事项id
	 */
	@ApiModelProperty("事项id")
	private int ModelId;

	/**
	 * 预约时间
	 */
	@ApiModelProperty("预约时间")
	private String YYSJ;

	/**
	 * 审批时间
	 */
	@ApiModelProperty("审批时间")
	private String SPSJ;

	/**
	 * 案件类别
	 */
	@ApiModelProperty("案件类别")
	private String caseType;

	/**
	 * 指派人员
	 */
	@ApiModelProperty("指派人员")
	private String WorkerName;

	/**
	 * 排期开始时间
	 */
	@ApiModelProperty("排期开始时间")
	private String pqStart;

	/**
	 * 排期结束时间
	 */
	@ApiModelProperty("排期结束时间")
	private String pqEnd;

	/**
	 * 审批状态
	 */
	@ApiModelProperty("审批状态")
	private String State;

	/**
	 * 审批状态描述
	 */
	@ApiModelProperty("审批状态描述")
	private String StateText;

	/**
	 * 当前进度SpeedId
	 */
	@ApiModelProperty("当前进度SpeedId")
	private String SpeedId;

	/**
	 * 申请方当事人身份证号
	 */
	@ApiModelProperty("申请方当事人身份证号")
	private String sfzhA;
	
	@ApiModelProperty("申请方当事人姓名")
	private String nameA;
	
	@ApiModelProperty("被申请方当事人姓名")
	private String nameB;

	@ApiModelProperty("案件类型二级")
	private String caseTypeSecond;

	/**
	 * 被申请方当事人身份证号
	 */
	@ApiModelProperty("被申请方当事人身份证号")
	private String sfzhB;

	/**
	 * 受理费
	 */
	@ApiModelProperty("受理费")
	private String slf;

	/**
	 * 常规处理费
	 */
	@ApiModelProperty("常规处理费")
	private String cgclf;

	/**
	 * 特殊处理费
	 */
	@ApiModelProperty("特殊处理费")
	private String tsclf;

	/**
	 * 案件金额
	 */
	@ApiModelProperty("案件金额")
	private String ajje;

	/**
	 * 委派人
	 */
	@ApiModelProperty("委派人")
	private String wpPerson;

	/**
	 * 委派时间
	 */
	@ApiModelProperty("委派时间")
	private String wpTime;

	/**
	 * 是否在线办理（0-在线办理【默认在线】，1-现场办理）
	 */
	@ApiModelProperty("是否在线办理（0-在线办理【默认在线】，1-现场办理）")
	private String IsOnline;

	/**
	 * 是否在线办理文字描述
	 */
	@ApiModelProperty("是否在线办理文字描述")
	private String IsOnlineText;
	
	@ApiModelProperty("案件录音路径")
	private String recordUrl;
	
	@ApiModelProperty("案件录音是否存在")
	private String recordUrlText;
	
	/**
	 * 生成仲裁文书id
	 */
	@ApiModelProperty("生成仲裁文书id")
	private String DocId;
	
	@ApiModelProperty("取证时间")
	private String QZSJ;
	
	@ApiModelProperty("首次指派工作人员id")
	private String FairworkerId;
	
	@ApiModelProperty("被指派的次数")
	private String copyIdCount;
	
	@ApiModelProperty("会议室注册用户密码")
	private String userloginpass;
	
	@ApiModelProperty("会议室ID")
	private String roomId;
	
	@ApiModelProperty("服务器地址")
	private String serverIp;
	
	@ApiModelProperty("服务器端口")
	private String port;

	public String getSlf() {
		return slf;
	}

	public void setSlf(String slf) {
		this.slf = slf;
	}

	public String getCgclf() {
		return cgclf;
	}

	public void setCgclf(String cgclf) {
		this.cgclf = cgclf;
	}

	public String getTsclf() {
		return tsclf;
	}

	public void setTsclf(String tsclf) {
		this.tsclf = tsclf;
	}

	public String getAjje() {
		return ajje;
	}

	public void setAjje(String ajje) {
		this.ajje = ajje;
	}

	public String getCaseTypeSecond() {
		return caseTypeSecond;
	}

	public void setCaseTypeSecond(String caseTypeSecond) {
		this.caseTypeSecond = caseTypeSecond;
	}

	public String getUserloginpass() {
		return userloginpass;
	}

	public void setUserloginpass(String userloginpass) {
		this.userloginpass = userloginpass;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@ApiModelProperty("会议室密码")
	private String pass;

	public int getModelId() {
		return ModelId;
	}

	public void setModelId(int modelId) {
		ModelId = modelId;
	}

	public String getYYSJ() {
		return YYSJ;
	}

	public void setYYSJ(String yYSJ) {
		YYSJ = yYSJ;
	}

	public String getSPSJ() {
		return SPSJ;
	}

	public void setSPSJ(String sPSJ) {
		SPSJ = sPSJ;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getWorkerName() {
		return WorkerName;
	}

	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}

	public String getPqStart() {
		return pqStart;
	}

	public void setPqStart(String pqStart) {
		this.pqStart = pqStart;
	}

	public String getPqEnd() {
		return pqEnd;
	}

	public void setPqEnd(String pqEnd) {
		this.pqEnd = pqEnd;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getSfzhA() {
		return sfzhA;
	}

	public void setSfzhA(String sfzhA) {
		this.sfzhA = sfzhA;
	}

	public String getSfzhB() {
		return sfzhB;
	}

	public void setSfzhB(String sfzhB) {
		this.sfzhB = sfzhB;
	}

	public String getWpPerson() {
		return wpPerson;
	}

	public void setWpPerson(String wpPerson) {
		this.wpPerson = wpPerson;
	}

	public String getWpTime() {
		return wpTime;
	}

	public void setWpTime(String wpTime) {
		this.wpTime = wpTime;
	}

	public String getFairworkerId() {
		return FairworkerId;
	}

	public void setFairworkerId(String fairworkerId) {
		FairworkerId = fairworkerId;
	}

	public String getCopyIdCount() {
		return copyIdCount;
	}

	public void setCopyIdCount(String copyIdCount) {
		this.copyIdCount = copyIdCount;
	}

	public String getStateText() {
		String text="";

		switch (State) {
		case "0":
			text="待仲裁";
			break;
		case "1":
			text="已指派";
			break;
		case "2":
			text="已排期";
			break;
		case "3":
			text="仲裁中";
			break;
		case "4":
			text="撤销";
			break;
		case "5":
			text="维持";
			break;
		case "6":
			text="仲裁流转";
			break;
		case "7":
			text="再指派";
			break;
		default:
			break;
		}

		return text;
	}

	public String getSpeedId() {
		return SpeedId;
	}

	public void setSpeedId(String speedId) {
		SpeedId = speedId;
	}

	public String getIsOnline() {
		return IsOnline;
	}

	public void setIsOnline(String isOnline) {
		IsOnline = isOnline;
	}

	public String getIsOnlineText() {
		String text="";

		switch (IsOnline) {
		case "0":
			text="线上";
			break;
		case "1":
			text="现场";
			break;
		default:
			break;
		}

		return text;
	}
	
	public String getDocId() {
		return DocId;
	}

	public void setDocId(String docId) {
		DocId = docId;
	}
	
	public String getRecordUrl() {
		return recordUrl;
	}

	public void setRecordUrl(String recordUrl) {
		this.recordUrl = recordUrl;
	}
	
	public String getRecordUrlText() {
		return (recordUrl==null||recordUrl.equals(""))?"无":"有";
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
	

	public String getQZSJ() {
		return QZSJ;
	}

	public void setQZSJ(String qZSJ) {
		QZSJ = qZSJ;
	}
}
