package com.xzx.viewModel;

import com.xzx.model.Speed;

import io.swagger.annotations.ApiModelProperty;

public class SpeedInfos {

	@ApiModelProperty("事项进度相关信息")
	private Speed speed;

	@ApiModelProperty("最新预约时间")
	private String newTime;

	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public String getNewTime() {
		return newTime;
	}

	public void setNewTime(String newTime) {
		this.newTime = newTime;
	}
}
