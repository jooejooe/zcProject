package com.xzx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "PostAuthentic")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "PostAuthentic", description = "TODO")

public class PostAuthentic implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "案件ID", required = true)
    private int ajid;
    @ApiModelProperty(value = "申请方ID", required = true)
    private String jfid;
    @ApiModelProperty(value = "申请方姓名", required = true)
    private String jfxm;
    @ApiModelProperty(value = "申请方身份证", required = true)
    private String jfsfzh;
    @ApiModelProperty(value = "被申请方ID", required = true)
    private String yfid;
    @ApiModelProperty(value = "被申请方姓名", required = true)
    private String yfxm;
    @ApiModelProperty(value = "被申请方身份证", required = true)
    private String yfsfzh;
    @ApiModelProperty(value = "案件类别", required = true)
    private String ajlb;
    @ApiModelProperty(value = "案件状态", required = true)
    private String ajzt;
    @ApiModelProperty(value = "预约时间", required = true)
    private String yysj;
    @ApiModelProperty(value = "创建时间", required = true)
    private String czsj;
    @ApiModelProperty(value = "工作人员ID", required = true)
    private String workerid;

}

