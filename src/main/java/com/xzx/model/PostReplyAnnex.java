package com.xzx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "Post")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "PostReplyAnnex", description = "TODO")

public class PostReplyAnnex implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "附件ID", required = true)
    private int fjid;
    @ApiModelProperty(value = "案件ID", required = true)
    private int ajid;
    @ApiModelProperty(value = "案件类型", required = true)
    private int fjlx;
    @ApiModelProperty(value = "附件名称", required = true)
    private String fjmc;
    @ApiModelProperty(value = "附件地址", required = true)
    private String fjurl;
}


