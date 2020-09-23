package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用户注册
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public class Register extends Model<Register> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "UserId", type = IdType.AUTO)
    @ApiModelProperty("用户ID")
    private Integer UserId;

    /**
     * 用户姓名
     */
    @JsonProperty(value = "RealName")
    @ApiModelProperty("用户姓名")
    private String RealName;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String SFZH;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String Telephone;

    /**
     * 性别 
     */
    @ApiModelProperty("性别")
    private String Sex;

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String Email;

    /**
     * QQ号
     */
    @ApiModelProperty("QQ号")
    private String QQ;

    /**
     * 联系地址
     */
    @ApiModelProperty("地址")
    private String Address;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String City;

    /**
     * 邮政编码
     */
    @ApiModelProperty("邮政编码")
    private String PostalCode;

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private String Birthday;

    /**
     * 学历
     */
    @ApiModelProperty("学历")
    private Integer Education;

    /**
     * 工作单位
     */
    @ApiModelProperty("工作单位")
    private String WorkUnit;

    /**
     * 注册IP
     */
    @ApiModelProperty("注册IP")
    private String RegisterIP;

    /**
     * 注册日期
     */
    @ApiModelProperty("注册日期")
    private String ReGisterDate;

    /**
     * 最后一次登陆时间
     */
    @ApiModelProperty("最后一次登陆时间")
    private String LastLoginTime;

    /**
     * 停用状态 0 启用 1 停用
     */
    @ApiModelProperty("停用状态 0 启用 1 停用")
    private Integer LoginState;

    /**
     * 职业
     */
    @ApiModelProperty("职业")
    private Integer Occupation;
    
    /**
     * 民族
     */
    @ApiModelProperty("民族")
    private String MZ;
    
    /**
     * 证件类别
     */
    @ApiModelProperty("证件类别")
    private Integer CardType;
    
    /**
     * 所住地
     */
    @ApiModelProperty("所住地")
    private String SZD;
    
    /**
     * 详细所住地
     */
    @ApiModelProperty("详细所住地")
    private String NowSZD;
    
    @ApiModelProperty("密码")
    private String password;
    
    @ApiModelProperty("头像")
    private String Image;
    
    @ApiModelProperty("指纹")
    private String fingers;
    
    @ApiModelProperty("微信绑定唯一标识字段")
    private String openid;
    
    @ApiModelProperty("融云token")
    private String rytoken;
    
    @ApiModelProperty("融云userid")
    private String ryuserid;
    
    @ApiModelProperty("用户类别  0 当事人  1 对方当事人  2 仲裁员")
    private String userType;
    
    private String loginzh;
    
    private String czaddress;
    
    private String idcardoverdate;

    private Integer isdb;

    private Integer zcdwid;

    public Integer getZcdwid() {
        return zcdwid;
    }

    public void setZcdwid(Integer zcdwid) {
        this.zcdwid = zcdwid;
    }

    public String getLoginzh() {
		return loginzh;
	}

	public void setLoginzh(String loginzh) {
		this.loginzh = loginzh;
	}

	public String getCzaddress() {
		return czaddress;
	}

	public void setCzaddress(String czaddress) {
		this.czaddress = czaddress;
	}

	public String getIdcardoverdate() {
		return idcardoverdate;
	}

	public void setIdcardoverdate(String idcardoverdate) {
		this.idcardoverdate = idcardoverdate;
	}

	public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }
    @JsonProperty(value = "RealName")
    public String getRealName() {
        return RealName;
    }
    @JsonProperty(value = "RealName")
    public void setRealName(String RealName) {
        this.RealName = RealName;
    }
    public String getSFZH() {
        return SFZH;
    }

    public void setSFZH(String SFZH) {
        this.SFZH = SFZH;
    }
    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }
    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }
    public Integer getEducation() {
        return Education;
    }

    public void setEducation(Integer Education) {
        this.Education = Education;
    }
    public String getWorkUnit() {
        return WorkUnit;
    }

    public void setWorkUnit(String WorkUnit) {
        this.WorkUnit = WorkUnit;
    }
    public String getRegisterIP() {
        return RegisterIP;
    }

    public void setRegisterIP(String RegisterIP) {
        this.RegisterIP = RegisterIP;
    }
    public String getReGisterDate() {
        return ReGisterDate;
    }

    public void setReGisterDate(String ReGisterDate) {
        this.ReGisterDate = ReGisterDate;
    }
    public String getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(String LastLoginTime) {
        this.LastLoginTime = LastLoginTime;
    }
    public Integer getLoginState() {
        return LoginState;
    }

    public void setLoginState(Integer LoginState) {
        this.LoginState = LoginState;
    }
    public Integer getOccupation() {
        return Occupation;
    }

    public void setOccupation(Integer Occupation) {
        this.Occupation = Occupation;
    }
    
    public String getMZ() {
		return MZ;
	}

	public void setMZ(String mZ) {
		MZ = mZ;
	}

	public Integer getCardType() {
		return CardType;
	}

	public void setCardType(Integer cardType) {
		CardType = cardType;
	}

	public String getSZD() {
		return SZD;
	}

	public void setSZD(String sZD) {
		SZD = sZD;
	}

	public String getNowSZD() {
		return NowSZD;
	}

	public void setNowSZD(String nowSZD) {
		NowSZD = nowSZD;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}
	
	public String getFingers() {
		return fingers;
	}

	public void setFingers(String fingers) {
		this.fingers = fingers;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getRytoken() {
		return rytoken;
	}

	public void setRytoken(String rytoken) {
		this.rytoken = rytoken;
	}

	public String getRyuserid() {
		return ryuserid;
	}

	public void setRyuserid(String ryuserid) {
		this.ryuserid = ryuserid;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

    public Integer getIsdb() {
        return isdb;
    }

    public void setIsdb(Integer isdb) {
        this.isdb = isdb;
    }

    @Override
    protected Serializable pkVal() {
        return this.UserId;
    }

    @Override
    public String toString() {
        return "Register{" +
        "UserId=" + UserId +
        ", RealName=" + RealName +
        ", SFZH=" + SFZH +
        ", Telephone=" + Telephone +
        ", Sex=" + Sex +
        ", Email=" + Email +
        ", QQ=" + QQ +
        ", Address=" + Address +
        ", City=" + City +
        ", PostalCode=" + PostalCode +
        ", Birthday=" + Birthday +
        ", Education=" + Education +
        ", WorkUnit=" + WorkUnit +
        ", RegisterIP=" + RegisterIP +
        ", ReGisterDate=" + ReGisterDate +
        ", LastLoginTime=" + LastLoginTime +
        ", LoginState=" + LoginState +
        ", Occupation=" + Occupation +
        ", MZ=" + MZ +
        ", CardType=" + CardType +
        ", SZD=" + SZD +
        ", NowSZD=" + NowSZD +
        ", password=" + password +
        ", Image=" + Image +
        ", fingers=" + fingers +
        ", openid=" +openid+
        ", isdb=" +isdb+
        ", zcdwid=" +zcdwid+
        "}";
    }
}
