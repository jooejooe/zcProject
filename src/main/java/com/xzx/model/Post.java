package com.xzx.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "Post")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value = "Post", description = "TODO")

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "帖子ID", required = true)
    private int tzid;
    @ApiModelProperty(value = "案件ID", required = true)
    private int ajid;
    @ApiModelProperty(value = "申请方ID", required = true)
    private int jfid;
    @ApiModelProperty(value = "被申请方ID")
    private int yfid;
    @ApiModelProperty(value = "可看类别（0，甲乙可看，1甲可看）")
    private int kklx;
    @ApiModelProperty(value = "帖子标题", required = true)
    private String title;
    @ApiModelProperty(value = "消息内容", required = true)
    private String tznr;
    @ApiModelProperty(value = "创建人ID", required = true)
    private int cjrid;
    @ApiModelProperty(value = "创建时间", required = true)
    private String cjsj;
    @ApiModelProperty(value = "回复时间", required = true)
    private String hfjs;
    List<PostAnnex> listPostAnnex;

}