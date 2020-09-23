package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2019-11-01
 */
public class Photoinfos extends Model<Photoinfos> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 身份证号
     */
    @JsonProperty("SFZH") 
    private String SFZH;

    /**
     * 高拍仪图片流
     */
    @JsonProperty("imgBase64")
    private String imgBase64;
    
    /**
     * 生成图片路径
     */
    @JsonProperty("sxqmurl")
    private String sxqmurl;

	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @JsonIgnore
    public String getSFZH() {
        return SFZH;
    }

    @JsonIgnore
    public void setSFZH(String SFZH) {
        this.SFZH = SFZH;
    }

    @JsonIgnore
    public String getImgBase64() {
		return imgBase64;
	}

    @JsonIgnore
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
    
    @JsonIgnore
    public String getSxqmurl() {
		return sxqmurl;
	}
    
    @JsonIgnore
	public void setSxqmurl(String sxqmurl) {
		this.sxqmurl = sxqmurl;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Photoinfos{" +
        "id=" + id +
        ", SFZH=" + SFZH +
        ", imgBase64=" + imgBase64 +
        "}";
    }
}
