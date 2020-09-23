package com.xzx.viewModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhotoInfo {
	@JsonProperty("SFZH") 
	private String SFZH;

	@JsonProperty("imgBase64") 
	private String imgBase64;

	@JsonIgnore
	public String getSFZH() {
		return SFZH;
	}

	@JsonIgnore
	public void setSFZH(String sFZH) {
		SFZH = sFZH;
	}

	@JsonIgnore
	public String getImgBase64() {
		return imgBase64;
	}

	@JsonIgnore
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
}
