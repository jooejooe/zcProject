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
 * @since 2020-04-23
 */
public class Access_bczj extends Model<Access_bczj> {

    private static final long serialVersionUID = 1L;

    /**
     * 补充证据附件主键ID
     */
    @TableId(value = "access_bczj_Id", type = IdType.AUTO)
    @ApiModelProperty("补充证据附件主键ID")
    private Integer access_bczj_Id;

    /**
     * 类别：0 公证 1 仲裁 2 援助 3 鉴定 4 律师
     */
    @ApiModelProperty("类别：0 公证 1 仲裁 2 援助 3 鉴定 4 律师")
    private Integer Type;

    /**
     * 申办的案件ID主键
     */
    @ApiModelProperty("申办的案件ID主键")
    private Integer ajId;

    /**
     * 附件名称
     */
    @ApiModelProperty("附件名称")
    private String accessname;

    /**
     * 附件存储地址
     */
    @ApiModelProperty("附件存储地址")
    private String accesscontext;

    public Integer getAccess_bczj_Id() {
        return access_bczj_Id;
    }

    public void setAccess_bczj_Id(Integer access_bczj_Id) {
        this.access_bczj_Id = access_bczj_Id;
    }
    public Integer getType() {
        return Type;
    }

    public void setType(Integer Type) {
        this.Type = Type;
    }
    public Integer getAjId() {
        return ajId;
    }

    public void setAjId(Integer ajId) {
        this.ajId = ajId;
    }
    public String getAccessname() {
        return accessname;
    }

    public void setAccessname(String accessname) {
        this.accessname = accessname;
    }
    public String getAccesscontext() {
        return accesscontext;
    }

    public void setAccesscontext(String accesscontext) {
        this.accesscontext = accesscontext;
    }

    @Override
    protected Serializable pkVal() {
        return this.access_bczj_Id;
    }

    @Override
    public String toString() {
        return "Access_bczj{" +
        "access_bczj_Id=" + access_bczj_Id +
        ", Type=" + Type +
        ", ajId=" + ajId +
        ", accessname=" + accessname +
        ", accesscontext=" + accesscontext +
        "}";
    }
}
