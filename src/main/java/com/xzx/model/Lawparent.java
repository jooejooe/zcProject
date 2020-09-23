package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2019-10-09
 */
public class Lawparent extends Model<Lawparent> {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	@ApiModelProperty("援助类别名称")
	@TableId(value = "lawParentId", type = IdType.AUTO)
	private Integer lawParentId;

	/**
	 * 援助类别名称
	 */
	@ApiModelProperty("援助类别题目标题")
	private String helpName;

	/**
	 * 步骤排序
	 */
	@ApiModelProperty("步骤排序")
	private Integer helpSort;

	/**
	 * 援助类别的类型 0 抚养费 关联援助类别表主键ID  
	 */
	@ApiModelProperty("援助类别的类型id")
	private Integer assistanceId;

	/**
	 * 类别名称
	 */
	@ApiModelProperty("援助类别名称")
	private String HelpTypeName;

	/**
	 * 是否删除 0 未删除  1 已删除
	 */
	@ApiModelProperty("援助类别名称")
	private Integer IsDel;

	/**
	 * 是否调用下级表接口 0 调用  1 不调用
	 */
	@ApiModelProperty("是否调用下级表接口 0 调用  1 不调用")
	private Integer State;
	
	@ApiModelProperty("控件类型（0 单个时间  1 起止时间  2 地点  3 年龄）")
	private Integer ControlType;

	public Integer getLawParentId() {
		return lawParentId;
	}

	public void setLawParentId(Integer lawParentId) {
		this.lawParentId = lawParentId;
	}
	public String getHelpName() {
		return helpName;
	}

	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}
	public Integer getHelpSort() {
		return helpSort;
	}

	public void setHelpSort(Integer helpSort) {
		this.helpSort = helpSort;
	}
	public Integer getAssistanceId() {
		return assistanceId;
	}

	public void setAssistanceId(Integer assistanceId) {
		this.assistanceId = assistanceId;
	}
	public String getHelpTypeName() {
		return HelpTypeName;
	}

	public void setHelpTypeName(String HelpTypeName) {
		this.HelpTypeName = HelpTypeName;
	}
	public Integer getIsDel() {
		return IsDel;
	}

	public void setIsDel(Integer IsDel) {
		this.IsDel = IsDel;
	}
	public Integer getState() {
		return State;
	}

	public void setState(Integer State) {
		this.State = State;
	}
	

	public Integer getControlType() {
		return ControlType;
	}

	public void setControlType(Integer controlType) {
		ControlType = controlType;
	}

	@Override
	protected Serializable pkVal() {
		return this.lawParentId;
	}

	@Override
	public String toString() {
		return "Lawparent{" +
				"lawParentId=" + lawParentId +
				", helpName=" + helpName +
				", helpSort=" + helpSort +
				", assistanceId=" + assistanceId +
				", HelpTypeName=" + HelpTypeName +
				", IsDel=" + IsDel +
				", State=" + State +
				"}";
	}
}
