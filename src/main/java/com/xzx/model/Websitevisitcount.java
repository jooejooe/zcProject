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
 * @since 2020-02-13
 */
public class Websitevisitcount extends Model<Websitevisitcount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 网站每日访问量
     */
    private Integer count1;

    /**
     * 网站访问总量
     */
    private Integer count2;

    /**
     * 网站季度访问量
     */
    private Integer count3;

    /**
     * 网站月度访问量
     */
    private Integer count4;

    /**
     * 备用字段1
     */
    private Integer count5;

    /**
     * 备用字段2
     */
    private Integer count6;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }
    public Integer getCount2() {
        return count2;
    }

    public void setCount2(Integer count2) {
        this.count2 = count2;
    }
    public Integer getCount3() {
        return count3;
    }

    public void setCount3(Integer count3) {
        this.count3 = count3;
    }
    public Integer getCount4() {
        return count4;
    }

    public void setCount4(Integer count4) {
        this.count4 = count4;
    }
    public Integer getCount5() {
        return count5;
    }

    public void setCount5(Integer count5) {
        this.count5 = count5;
    }
    public Integer getCount6() {
        return count6;
    }

    public void setCount6(Integer count6) {
        this.count6 = count6;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Websitevisitcount{" +
        "id=" + id +
        ", count1=" + count1 +
        ", count2=" + count2 +
        ", count3=" + count3 +
        ", count4=" + count4 +
        ", count5=" + count5 +
        ", count6=" + count6 +
        "}";
    }
}
