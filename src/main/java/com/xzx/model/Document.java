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
 * @since 2019-12-16
 */
public class Document extends Model<Document> {

    private static final long serialVersionUID = 1L;

    /**
     * 文书ID
     */
    @TableId(value = "DocId", type = IdType.AUTO)
    @ApiModelProperty("文书ID")
    private Integer DocId;

    /**
     * 文书标题
     */
    @ApiModelProperty("文书标题")
    private String Title;

    /**
     * 案卷号
     */
    @ApiModelProperty("案卷号")
    private String FileNumber;

    /**
     * 文书地址
     */
    @ApiModelProperty("文书地址")
    private String FilePath;

    /**
     * 关键词
     */
    @ApiModelProperty("关键词")
    private String KeyWord;

    /**
     * 申请方姓名
     */
    @ApiModelProperty("申请方姓名")
    private String PartyAName;

    /**
     * 申请方身份证号
     */
    @ApiModelProperty("申请方身份证号")
    private String PartyAIdCard;

    /**
     * 申请方手机号
     */
    @ApiModelProperty("申请方手机号")
    private String PartyAPhone;

    /**
     * 被申请方姓名
     */
    @ApiModelProperty("被申请方姓名")
    private String PartyBName;

    /**
     * 被申请方身份证号
     */
    @ApiModelProperty("被申请方身份证号")
    private String PartyBIdCard;

    /**
     * 被申请方手机号
     */
    @ApiModelProperty("被申请方手机号")
    private String PartyBPhone;

    /**
     * 第三方姓名
     */
    @ApiModelProperty("第三方姓名")
    private String PartyCName;

    /**
     * 第三方身份证号
     */
    @ApiModelProperty("第三方身份证号")
    private String PartyCIdCard;

    /**
     * 第三方身手机号
     */
    @ApiModelProperty("第三方身手机号")
    private String PartyCPhone;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String CreateDate;
    
    /**
     * 文书类别 0 法律公证 1 司法鉴定 2 人民仲裁 3 法律援助 4 律师
     */
    @ApiModelProperty("文书类别 0 法律公证 1 司法鉴定 2 人民仲裁 3 法律援助 4 律师")
    private String DocType;
    
    /**
     * 对应业务的业务id
     */
    @ApiModelProperty("对应业务的业务id")
    private int CaseId;

	public Integer getDocId() {
        return DocId;
    }

    public void setDocId(Integer DocId) {
        this.DocId = DocId;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getFileNumber() {
        return FileNumber;
    }

    public void setFileNumber(String FileNumber) {
        this.FileNumber = FileNumber;
    }
    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }
    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String KeyWord) {
        this.KeyWord = KeyWord;
    }
    public String getPartyAName() {
        return PartyAName;
    }

    public void setPartyAName(String PartyAName) {
        this.PartyAName = PartyAName;
    }
    public String getPartyAIdCard() {
        return PartyAIdCard;
    }

    public void setPartyAIdCard(String PartyAIdCard) {
        this.PartyAIdCard = PartyAIdCard;
    }
    public String getPartyAPhone() {
        return PartyAPhone;
    }

    public void setPartyAPhone(String PartyAPhone) {
        this.PartyAPhone = PartyAPhone;
    }
    public String getPartyBName() {
        return PartyBName;
    }

    public void setPartyBName(String PartyBName) {
        this.PartyBName = PartyBName;
    }
    public String getPartyBIdCard() {
        return PartyBIdCard;
    }

    public void setPartyBIdCard(String PartyBIdCard) {
        this.PartyBIdCard = PartyBIdCard;
    }
    public String getPartyBPhone() {
        return PartyBPhone;
    }

    public void setPartyBPhone(String PartyBPhone) {
        this.PartyBPhone = PartyBPhone;
    }
    public String getPartyCName() {
        return PartyCName;
    }

    public void setPartyCName(String PartyCName) {
        this.PartyCName = PartyCName;
    }
    public String getPartyCIdCard() {
        return PartyCIdCard;
    }

    public void setPartyCIdCard(String PartyCIdCard) {
        this.PartyCIdCard = PartyCIdCard;
    }
    public String getPartyCPhone() {
        return PartyCPhone;
    }

    public void setPartyCPhone(String PartyCPhone) {
        this.PartyCPhone = PartyCPhone;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
    
    public String getDocType() {
		return DocType;
	}

	public void setDocType(String docType) {
		DocType = docType;
	}
	
	public int getCaseId() {
		return CaseId;
	}

	public void setCaseId(int caseId) {
		CaseId = caseId;
	}

    @Override
    protected Serializable pkVal() {
        return this.DocId;
    }

    @Override
    public String toString() {
        return "Document{" +
        "DocId=" + DocId +
        ", Title=" + Title +
        ", FileNumber=" + FileNumber +
        ", FilePath=" + FilePath +
        ", KeyWord=" + KeyWord +
        ", PartyAName=" + PartyAName +
        ", PartyAIdCard=" + PartyAIdCard +
        ", PartyAPhone=" + PartyAPhone +
        ", PartyBName=" + PartyBName +
        ", PartyBIdCard=" + PartyBIdCard +
        ", PartyBPhone=" + PartyBPhone +
        ", PartyCName=" + PartyCName +
        ", PartyCIdCard=" + PartyCIdCard +
        ", PartyCPhone=" + PartyCPhone +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
