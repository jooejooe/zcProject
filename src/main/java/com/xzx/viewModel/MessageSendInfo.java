package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

public class MessageSendInfo {
	/**
	 * 要发送短信的手机号码字符串集合
	 */
	@ApiModelProperty("要发送短信的手机号码字符串集合")
	private String Telephones;

	/**
	 * 发送短信的参数集合
	 */
	@ApiModelProperty("发送短信的参数集合")
	private String Params;
	
	/**
	 * 是否单条发送
	 */
	@ApiModelProperty("是否单条发送")
	private Boolean isSingle;
	
	/**
	 * 区域id或者token
	 */
	@ApiModelProperty("区域id或者token")
	private String RegionIdOrToken;
	
	/**
	 * 状态
	 */
	@ApiModelProperty("状态")
	private int State;

	/**
	 * 工作人员还是普通用户
	 */
	@ApiModelProperty("工作人员还是普通用户【0-普通用户；1-工作人员】")
	private int WorkerType;
	
	/**
	 * 短信签名
	 */
	@ApiModelProperty("短信签名")
	private String Sign;
	
	/**
	 * 传入值是token还是区域regionId（0-token;1-regionId）
	 */
	@ApiModelProperty("传入值是token还是区域regionId（0-token;1-regionId）")
	private int valueType;
	
	/**
	 * 区域id
	 */
	@ApiModelProperty("区域id")
	private int regionId;
	
	/**
	 * 模板id
	 */
	@ApiModelProperty("模板id")
	private int templateId;
	
	/**
	 * 短信类型（0-通知类；1-验证码）
	 */
	@ApiModelProperty("短信类型（0-通知类；1-验证码）")
	private int messageType;
	
	/**
	 * 0-在线办理；1-预约
	 */
	private int itemType;
	
	/**
	 * 业务类型
	 * 预约：0：公证办理预约，1：人民仲裁预约，2：司法鉴定预约，3：法律援助预约，4：律师预约
	 * 在线：0：公证办理，1：人民仲裁，2：司法鉴定，3：法律援助
	 */
	private int modelType;
	
	/**
	 * 0-未删除；1-已删除
	 */
	private int is_delete;

	public String getTelephones() {
		return Telephones;
	}

	public void setTelephones(String telephones) {
		Telephones = telephones;
	}

	public String getParams() {
		return Params;
	}

	public void setParams(String params) {
		Params = params;
	}
	
	public Boolean getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(Boolean isSingle) {
		this.isSingle = isSingle;
	}

	public String getRegionIdOrToken() {
		return RegionIdOrToken;
	}

	public void setRegionIdOrToken(String regionIdOrToken) {
		RegionIdOrToken = regionIdOrToken;
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}

	public int getWorkerType() {
		return WorkerType;
	}

	public void setWorkerType(int workerType) {
		WorkerType = workerType;
	}
	
	public String getSign() {
		return Sign;
	}

	public void setSign(String sign) {
		Sign = sign;
	}
	
	public int getValueType() {
		return valueType;
	}

	public void setValueType(int valueType) {
		this.valueType = valueType;
	}
	
	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getModelType() {
		return modelType;
	}

	public void setModelType(int modelType) {
		this.modelType = modelType;
	}
	

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
}
