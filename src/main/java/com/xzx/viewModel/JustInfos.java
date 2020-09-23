package com.xzx.viewModel;

import java.util.List;

import com.xzx.model.Access;
import com.xzx.model.Just;

import io.swagger.annotations.ApiModelProperty;

/**
 * 公证办理ViewModel
 * @author Administrator
 *
 */
public class JustInfos 
{
	@ApiModelProperty("公证办理事项")
	private Just just;
	
	@ApiModelProperty("公证办理相关附件(添加时若没有附件此字段不传)")
	private List<Access> listAccess;

	public Just getJust() {
		return just;
	}

	public void setJust(Just just) {
		this.just = just;
	}

	public List<Access> getListAccess() {
		return listAccess;
	}

	public void setListAccess(List<Access> listAccess) {
		this.listAccess = listAccess;
	}
}
