package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2019-12-17
 */
public class Sampletables extends Model<Sampletables> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 表格标题
     */
    private String title;

    /**
     * 示例地址
     */
    private String examplePath;

    /**
     * 模板地址
     */
    private String templatePath;

    /**
     * 机构id
     */
    private Integer departmentId;

    /**
     * 区域id
     */
    private Integer regionId;

    /**
     * 正常0；删除1
     */
    private Integer isDel;

    /**
     * 启用0；停用1
     */
    private Integer state;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;
    
    private int modelType;

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
    public String getExamplePath() {
        return examplePath;
    }

    public void setExamplePath(String examplePath) {
        this.examplePath = examplePath;
    }
    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public int getModelType() {
		return modelType;
	}

	public void setModelType(int modelType) {
		this.modelType = modelType;
	}

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sampletables{" +
        "id=" + id +
        ", title=" + title +
        ", examplePath=" + examplePath +
        ", templatePath=" + templatePath +
        ", departmentId=" + departmentId +
        ", regionId=" + regionId +
        ", isDel=" + isDel +
        ", state=" + state +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", modelType=" +modelType +
        "}";
    }
}
