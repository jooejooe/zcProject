package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 申办事项
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public class Biddingmatters extends Model<Biddingmatters> {

    private static final long serialVersionUID = 1L;

    /**
     * 申办业务主键
     */
    @TableId(value = "BiddingMattersId", type = IdType.AUTO)
    private Integer BiddingMattersId;

    /**
     * 申办事项标题
     */
    private String BiddingMattersTitle;

    /**
     * 使用状态 0 启用 1 未启用
     */
    private Integer State;

    private String CreateDate;

    public Integer getBiddingMattersId() {
        return BiddingMattersId;
    }

    public void setBiddingMattersId(Integer BiddingMattersId) {
        this.BiddingMattersId = BiddingMattersId;
    }
    public String getBiddingMattersTitle() {
        return BiddingMattersTitle;
    }

    public void setBiddingMattersTitle(String BiddingMattersTitle) {
        this.BiddingMattersTitle = BiddingMattersTitle;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.BiddingMattersId;
    }

    @Override
    public String toString() {
        return "Biddingmatters{" +
        "BiddingMattersId=" + BiddingMattersId +
        ", BiddingMattersTitle=" + BiddingMattersTitle +
        ", State=" + State +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
