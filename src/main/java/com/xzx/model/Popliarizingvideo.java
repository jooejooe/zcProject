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
public class Popliarizingvideo extends Model<Popliarizingvideo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PopliarizingVideoId", type = IdType.AUTO)
    private Integer PopliarizingVideoId;

    /**
     * 普法宣传视频标题
     */
    private String Title;

    /**
     * 普法宣传视频封面
     */
    private String Cover;

    /**
     * 关联的新闻类型
     */
    private Integer NewsTypeId;

    /**
     * 普法宣传的视频
     */
    private String Video;

    /**
     * 是否可用 0 可用 1不可用
     */
    private Integer IsRos;

    /**
     * 创建时间
     */
    private String CreateDate;

    public Integer getPopliarizingVideoId() {
        return PopliarizingVideoId;
    }

    public void setPopliarizingVideoId(Integer PopliarizingVideoId) {
        this.PopliarizingVideoId = PopliarizingVideoId;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getCover() {
        return Cover;
    }

    public void setCover(String Cover) {
        this.Cover = Cover;
    }
    public Integer getNewsTypeId() {
        return NewsTypeId;
    }

    public void setNewsTypeId(Integer NewsTypeId) {
        this.NewsTypeId = NewsTypeId;
    }
    public String getVideo() {
        return Video;
    }

    public void setVideo(String Video) {
        this.Video = Video;
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
        return this.PopliarizingVideoId;
    }

    @Override
    public String toString() {
        return "Popliarizingvideo{" +
        "PopliarizingVideoId=" + PopliarizingVideoId +
        ", Title=" + Title +
        ", Cover=" + Cover +
        ", NewsTypeId=" + NewsTypeId +
        ", Video=" + Video +
        ", IsRos=" + IsRos +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
