package com.xzx.viewModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FingersInfo {
	@JsonProperty("SFZH") 
	private String SFZH;

	@JsonProperty("fingers") 
	private String fingers;

	@JsonIgnore
	public String getSFZH() {
		return SFZH;
	}

	@JsonIgnore
	public void setSFZH(String sFZH) {
		SFZH = sFZH;
	}

	@JsonIgnore
	public String getFingers() {
		return fingers;
	}

	@JsonIgnore
	public void setFingers(String fingers) {
		this.fingers = fingers;
	}
}
