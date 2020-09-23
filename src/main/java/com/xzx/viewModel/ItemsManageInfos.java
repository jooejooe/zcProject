package com.xzx.viewModel;

import java.util.List;

import com.xzx.model.Access;
import com.xzx.model.Appraisal;
import com.xzx.model.Authentic;
import com.xzx.model.Lawhelp;
import com.xzx.model.Otherparty;

import io.swagger.annotations.ApiModelProperty;

public class ItemsManageInfos {
	
	@ApiModelProperty("人民仲裁相关信息")
	private Authentic authentic;
	
	@ApiModelProperty("司法鉴定相关信息")
	private Appraisal appraisal;
	
	@ApiModelProperty("法律援助相关信息")
	private Lawhelp lawhelp;

	@ApiModelProperty("对方当事人相关相关信息")
	private Otherparty otherparty;
	
/*	@ApiModelProperty("当前登录人相关信息")
	private Register register;*/
	
	@ApiModelProperty("附件列表")
	private List<Access> listAccess;
	
	@ApiModelProperty("业务类型【0 公证 1 人民仲裁 2 司法鉴定 3 法律援助】")
	private String type;
	
	@ApiModelProperty("用户id")
	private String userId;

	public Authentic getAuthentic() {
		return authentic;
	}
	public void setAuthentic(Authentic authentic) {
		this.authentic = authentic;
	}
	public Appraisal getAppraisal() {
		return appraisal;
	}
	public void setAppraisal(Appraisal appraisal) {
		this.appraisal = appraisal;
	}
	public Lawhelp getLawhelp() {
		return lawhelp;
	}
	public void setLawhelp(Lawhelp lawhelp) {
		this.lawhelp = lawhelp;
	}
	public Otherparty getOtherparty() {
		return otherparty;
	}
	public void setOtherparty(Otherparty otherparty) {
		this.otherparty = otherparty;
	}
/*	public Register getRegister() {
		return register;
	}
	public void setRegister(Register register) {
		this.register = register;
	}*/
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Access> getListAccess() {
		return listAccess;
	}
	public void setListAccess(List<Access> listAccess) {
		this.listAccess = listAccess;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
