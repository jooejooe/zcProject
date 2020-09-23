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
public class News extends Model<News> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("新闻id")
    @TableId(value = "NewsId", type = IdType.AUTO)
    private Integer NewsId;

    /**
     * 新闻标题
     */
    @ApiModelProperty("新闻标题")
    private String NewsTitle;

    /**
     * 司法要闻类别ID
     */
    @ApiModelProperty("司法要闻类别ID")
    private Integer NewsTypeId;

    /**
     * 上传新闻封面图片
     */
    @ApiModelProperty("上传新闻封面图片")
    private String NewsCover;

    /**
     * 区域ID
     */
    @ApiModelProperty("区域ID")
    private Integer AreaId;

    /**
     * 展示时间
     */
    @ApiModelProperty("展示时间")
    private String DisplayTime;

    /**
     * 新闻简介
     */
    @ApiModelProperty("新闻简介")
    private String NewsBrief;

    /**
     * 新闻详情
     */
    @ApiModelProperty("新闻详情")
    private String NewsContext;

    /**
     * 是否推荐(0:推荐,1:未推荐)
     */
    private Integer IsRecommend;

    /**
     * 是否启用(0:启用,1:未启用)
     */
    private Integer IsAvailable;

    public Integer getNewsId() {
        return NewsId;
    }

    public void setNewsId(Integer NewsId) {
        this.NewsId = NewsId;
    }
    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String NewsTitle) {
        this.NewsTitle = NewsTitle;
    }
    public Integer getNewsTypeId() {
        return NewsTypeId;
    }

    public void setNewsTypeId(Integer NewsTypeId) {
        this.NewsTypeId = NewsTypeId;
    }
    public String getNewsCover() {
        return NewsCover;
    }

    public void setNewsCover(String NewsCover) {
        this.NewsCover = NewsCover;
    }
    public Integer getAreaId() {
        return AreaId;
    }

    public void setAreaId(Integer AreaId) {
        this.AreaId = AreaId;
    }
    public String getDisplayTime() {
        return DisplayTime;
    }

    public void setDisplayTime(String DisplayTime) {
        this.DisplayTime = DisplayTime;
    }
    public String getNewsBrief() {
        return NewsBrief;
    }

    public void setNewsBrief(String NewsBrief) {
        this.NewsBrief = NewsBrief;
    }
    public String getNewsContext() {
        return NewsContext;
    }

    public void setNewsContext(String NewsContext) {
        this.NewsContext = NewsContext;
    }
    public Integer getIsRecommend() {
        return IsRecommend;
    }

    public void setIsRecommend(Integer IsRecommend) {
        this.IsRecommend = IsRecommend;
    }
    public Integer getIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(Integer IsAvailable) {
        this.IsAvailable = IsAvailable;
    }

    @Override
    protected Serializable pkVal() {
        return this.NewsId;
    }

    @Override
    public String toString() {
        return "News{" +
        "NewsId=" + NewsId +
        ", NewsTitle=" + NewsTitle +
        ", NewsTypeId=" + NewsTypeId +
        ", NewsCover=" + NewsCover +
        ", AreaId=" + AreaId +
        ", DisplayTime=" + DisplayTime +
        ", NewsBrief=" + NewsBrief +
        ", NewsContext=" + NewsContext +
        ", IsRecommend=" + IsRecommend +
        ", IsAvailable=" + IsAvailable +
        "}";
    }
}
