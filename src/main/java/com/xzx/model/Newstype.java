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
 * @since 2019-10-08
 */
public class Newstype extends Model<Newstype> {

    private static final long serialVersionUID = 1L;

    /**
     * 新闻类别ID
     */
    @ApiModelProperty("新闻类别ID")
    @TableId(value = "NewsTypeId", type = IdType.AUTO)
    private Integer NewsTypeId;

    /**
     * 司法要闻名称
     */
    @ApiModelProperty("司法要闻名称")
    private String NewsTypeName;

    /**
     * 新闻使用模型 0 司法要闻 1 普法宣传新闻  2 普法宣传视频
     */
    @ApiModelProperty("新闻使用模型 0 司法要闻 1 普法宣传新闻  2 普法宣传视频")
    private Integer NewsWorkModel;

    public Integer getNewsTypeId() {
        return NewsTypeId;
    }

    public void setNewsTypeId(Integer NewsTypeId) {
        this.NewsTypeId = NewsTypeId;
    }
    public String getNewsTypeName() {
        return NewsTypeName;
    }

    public void setNewsTypeName(String NewsTypeName) {
        this.NewsTypeName = NewsTypeName;
    }
    public Integer getNewsWorkModel() {
        return NewsWorkModel;
    }

    public void setNewsWorkModel(Integer NewsWorkModel) {
        this.NewsWorkModel = NewsWorkModel;
    }

    @Override
    protected Serializable pkVal() {
        return this.NewsTypeId;
    }

    @Override
    public String toString() {
        return "Newstype{" +
        "NewsTypeId=" + NewsTypeId +
        ", NewsTypeName=" + NewsTypeName +
        ", NewsWorkModel=" + NewsWorkModel +
        "}";
    }
}
