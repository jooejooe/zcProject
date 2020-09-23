package com.xzx.viewModel;

import io.swagger.annotations.ApiModelProperty;

public class ProgressInfos {
	@ApiModelProperty("申请时间")
	private String CreateDate;

	@ApiModelProperty("进度id")
	private String SpeedId;

	@ApiModelProperty("历史状态code值字符串")
	private String StateStr;

	@ApiModelProperty("业务id")
	private String ModelId;

	@ApiModelProperty("业务类型")
	private String ModelType;

	@ApiModelProperty("申办类型")
	private String typeStr;

	@ApiModelProperty("历史状态文字值字符串")
	private String StateTextStr;
	
	@ApiModelProperty("数据来源：0-在线办理，1-现场办理")
	private String IsOnline;

	public String getStateTextStr() {
		String onlineModelType="0,1,2,3";

		if(onlineModelType.indexOf(this.ModelType)>=0)//在线申办
		{
			if(IsOnline.equals("0"))
			{
				if(ModelType.equals("1"))
					return this.StateStr.replace("0", "待仲裁").replace("1", "已指派").replace("2", "已排期").replace("3", "仲裁中")
							.replace("4", "撤销").replace("5", "维持").replace("6", "仲裁流转").replace("7", "再指派");//.replace("5", "诉讼引导")
				else 
					return this.StateStr.replace("0", "待审批").replace("1", "审批中").replace("2", "审批通过").replace("3", "补充材料")
						.replace("4", "已撤销").replace("5", "未通过");
			}			
			else
				return this.StateStr.replace("0", "待仲裁").replace("1", "已指派").replace("2", "已排期").replace("3", "仲裁中")
						.replace("4", "撤销").replace("5", "维持").replace("6", "仲裁流转").replace("7", "再指派");//.replace("5", "诉讼引导")
		}
		else//预约
		{
			return this.StateStr.replace("0", "待确定").replace("1", "预约成功").replace("2", "预约失败").replace("3", "更改预约时间").replace("4", "补充材料");
		}
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getSpeedId() {
		return SpeedId;
	}

	public void setSpeedId(String speedId) {
		SpeedId = speedId;
	}

	public String getStateStr() {		
		return StateStr;
	}

	public void setStateStr(String stateStr) {
		StateStr = stateStr;
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

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
}
