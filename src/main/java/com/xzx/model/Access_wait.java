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
 * @since 2020-08-27
 */
public class Access_wait extends Model<Access_wait> {

    private static final long serialVersionUID = 1L;

    /**
     * 等候区上传证据主键ID
     */
    @TableId(value = "access_waitId", type = IdType.AUTO)
    private Integer access_waitId;

    /**
     * 上传证据当事人或对方当事人主键ID
     */
    private Integer userId;

    /**
     * 证据上传时间
     */
    private String uploaddate;

    /**
     * 上传证据至服务器的返回路径
     */
    private String accessurl;

    /**
     * 案件ID
     */
    private Integer anjianId;
    
    private String accessname;
    
    private String usertype;

	public Integer getAccess_waitId() {
        return access_waitId;
    }

    public void setAccess_waitId(Integer access_waitId) {
        this.access_waitId = access_waitId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(String uploaddate) {
        this.uploaddate = uploaddate;
    }
    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }
    public Integer getAnjianId() {
        return anjianId;
    }

    public void setAnjianId(Integer anjianId) {
        this.anjianId = anjianId;
    }
    

    public String getAccessname() {
		return accessname;
	}

	public void setAccessname(String accessname) {
		this.accessname = accessname;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

    @Override
    protected Serializable pkVal() {
        return this.access_waitId;
    }

    @Override
    public String toString() {
        return "Access_wait{" +
        "access_waitId=" + access_waitId +
        ", userId=" + userId +
        ", uploaddate=" + uploaddate +
        ", accessurl=" + accessurl +
        ", anjianId=" + anjianId +
        "}";
    }
}
