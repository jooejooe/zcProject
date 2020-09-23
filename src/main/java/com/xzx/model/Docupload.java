package com.xzx.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2019-12-16
 */
public class Docupload extends Model<Docupload> {

    private static final long serialVersionUID = 1L;

    /**
     * 文书上传表
     */
    @TableId(value = "docId", type = IdType.AUTO)
    private Integer docId;

    /**
     * 标题名称
     */
    private String doctitle;

    /**
     * 文书类别 0 法律公正 1 司法鉴定 2 人民仲裁 3 法律援助
     */
    private Integer doctype;

    /**
     * 文书路径
     */
    private String docPath;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 创建时间
     */
    private String creatDate;

    /**
     * 是否删除 0 未删除 1 已删除
     */
    private Integer isDel;

    /**
     * 是否推送 0 未推送  1  已推送
     */
    private Integer isSend;

    /**
     * 区域id
     */
    private Integer regionId;

    /**
     * 机构id
     */
    private Integer departmentId;

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }
    public String getDoctitle() {
        return doctitle;
    }

    public void setDoctitle(String doctitle) {
        this.doctitle = doctitle;
    }
    public Integer getDoctype() {
        return doctype;
    }

    public void setDoctype(Integer doctype) {
        this.doctype = doctype;
    }
    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.docId;
    }

    @Override
    public String toString() {
        return "Docupload{" +
        "docId=" + docId +
        ", doctitle=" + doctitle +
        ", doctype=" + doctype +
        ", docPath=" + docPath +
        ", creator=" + creator +
        ", creatDate=" + creatDate +
        ", isDel=" + isDel +
        ", isSend=" + isSend +
        ", regionId=" + regionId +
        ", departmentId=" + departmentId +
        "}";
    }
}
