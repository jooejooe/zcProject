package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 申办事项与费用关系
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public class Biddingmanagersmoney extends Model<Biddingmanagersmoney> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BiddingMagttersMoneyId", type = IdType.AUTO)
    private Integer BiddingMagttersMoneyId;

    /**
     * 关联申办业务ID
     */
    private Integer BiddingMattersId;

    /**
     * 标题
     */
    private String Title;

    /**
     * 费用金额
     */
    private Float Money;

    /**
     * 所需附件个数
     */
    private Integer Accessnumber;

    /**
     * 所需附件个数，以逗号分隔
     */
    private String AccessNameAll;

    /**
     * 启用状态 0 启用  1未启用
     */
    private Integer State;

    private String CreateDate;

    public Integer getBiddingMagttersMoneyId() {
        return BiddingMagttersMoneyId;
    }

    public void setBiddingMagttersMoneyId(Integer BiddingMagttersMoneyId) {
        this.BiddingMagttersMoneyId = BiddingMagttersMoneyId;
    }
    public Integer getBiddingMattersId() {
        return BiddingMattersId;
    }

    public void setBiddingMattersId(Integer BiddingMattersId) {
        this.BiddingMattersId = BiddingMattersId;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public Float getMoney() {
        return Money;
    }

    public void setMoney(Float Money) {
        this.Money = Money;
    }
    public Integer getAccessnumber() {
        return Accessnumber;
    }

    public void setAccessnumber(Integer Accessnumber) {
        this.Accessnumber = Accessnumber;
    }
    public String getAccessNameAll() {
        return AccessNameAll;
    }

    public void setAccessNameAll(String AccessNameAll) {
        this.AccessNameAll = AccessNameAll;
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
        return this.BiddingMagttersMoneyId;
    }

    @Override
    public String toString() {
        return "Biddingmanagersmoney{" +
        "BiddingMagttersMoneyId=" + BiddingMagttersMoneyId +
        ", BiddingMattersId=" + BiddingMattersId +
        ", Title=" + Title +
        ", Money=" + Money +
        ", Accessnumber=" + Accessnumber +
        ", AccessNameAll=" + AccessNameAll +
        ", State=" + State +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
