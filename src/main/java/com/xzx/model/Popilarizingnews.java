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
 * @since 2019-12-12
 */
public class Popilarizingnews extends Model<Popilarizingnews> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PopularizingNewsId", type = IdType.AUTO)
    private Integer PopularizingNewsId;

    /**
     * 标题
     */
    private String Title;

    /**
     * 文章类型
     */
    private Integer NewsTypeId;

    /**
     * 普法宣传封面
     */
    private String Cover;

    /**
     * 普法宣传简介
     */
    private String Infomation;

    /**
     * 普法宣传内容
     */
    private String Context;

    /**
     * 是否启用 0 启用 1 未启用
     */
    private Integer IsRos;

    /**
     * 创建时间
     */
    private String CreateDate;

    public Integer getPopularizingNewsId() {
        return PopularizingNewsId;
    }

    public void setPopularizingNewsId(Integer PopularizingNewsId) {
        this.PopularizingNewsId = PopularizingNewsId;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public Integer getNewsTypeId() {
        return NewsTypeId;
    }

    public void setNewsTypeId(Integer NewsTypeId) {
        this.NewsTypeId = NewsTypeId;
    }
    public String getCover() {
        return Cover;
    }

    public void setCover(String Cover) {
        this.Cover = Cover;
    }
    public String getInfomation() {
        return Infomation;
    }

    public void setInfomation(String Infomation) {
        this.Infomation = Infomation;
    }
    public String getContext() {
        return Context;
    }

    public void setContext(String Context) {
        this.Context = Context;
    }
    public Integer getIsRos() {
        return IsRos;
    }

    public void setIsRos(Integer IsRos) {
        this.IsRos = IsRos;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.PopularizingNewsId;
    }

    @Override
    public String toString() {
        return "Popilarizingnews{" +
        "PopularizingNewsId=" + PopularizingNewsId +
        ", Title=" + Title +
        ", NewsTypeId=" + NewsTypeId +
        ", Cover=" + Cover +
        ", Infomation=" + Infomation +
        ", Context=" + Context +
        ", IsRos=" + IsRos +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
