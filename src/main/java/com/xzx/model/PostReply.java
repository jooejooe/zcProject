package com.xzx.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Data
@XmlRootElement(name = "PostReply")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "PostReply", description = "TODO")

public class PostReply implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "回复ID", required = true)
    private int hfid;
    @ApiModelProperty(value = "案件ID", required = true)
    private int ajid;
    @ApiModelProperty(value = "回复内容", required = true)
    private String hfnr;
    @ApiModelProperty(value = "创建人ID", required = true)
    private int cjrid;
    @ApiModelProperty(value = "创建时间", required = true)
    private String cjsj;
    @ApiModelProperty(value = "创建人类型（0 工作人员，1甲被申请方）", required = true)
    private int cjrlx;
    List<PostReplyAnnex> listPostReplyAnnex;

}

