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
 * @since 2020-05-16
 */
public class Improveability extends Model<Improveability> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 资料类别（0-文档，1-视频）
     */
    @ApiModelProperty("资料类别（0-文档，1-视频）")
    private Integer docType;

    /**
     * 文档路径
     */
    @ApiModelProperty("文档路径")
    private String docUrl;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;
    
    @ApiModelProperty("创建人姓名")
    private String createPerson;
    
    @ApiModelProperty("0-未删除，1-已删除")
    private int isdel;
    
    @ApiModelProperty("附件文件名")
    private String filename;

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getDocType() {
        return docType;
    }

    public void setDocType(Integer docType) {
        this.docType = docType;
    }
    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }
    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Improveability{" +
        "id=" + id +
        ", title=" + title +
        ", docType=" + docType +
        ", docUrl=" + docUrl +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        "}";
    }
}
