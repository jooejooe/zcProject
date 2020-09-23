package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 工作人员
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public class Fairworker extends Model<Fairworker> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "FairWorkerId", type = IdType.AUTO)
    private Integer FairWorkerId;

    /**
     * 工作人员姓名
     */
    private String WorkerName;

    /**
     * 姓名汉语拼音
     */
    private String PinYin;

    /**
     * 手机号码
     */
    private String Phone;

    /**
     * 照片
     */
    private String Image;

    /**
     * 性别 0 男 1 女
     */
    private String Sex;

    /**
     * 性别内容
     */
    private String sexText;

    public String getSexText() {
        if(Sex==null)
            return "";
        else if(Sex.equals("0"))
            return "男";
        else
            return "女";

    }


    /**
     * 政治面貌
     */
    private String PoliticalOutlook;

    /**
     * 专业特长
     */
    private String Speciality;

    /**
     * 身份证号
     */
    private String SFZH;

    /**
     * 民族
     */
    private String MZ;

    /**
     * 学历
     */
    private String Education;

    /**
     * 学位
     */
    private String Academic;

    /**
     * 所属机构
     */
    private Integer DepartmentId;

    /**
     * 专业职称
     */
    private String Professional;

    /**
     * 执业证书编码
     */
    private String Certificate;

    /**
     * 职务
     */
    private String Post;

    /**
     * 执业证书颁发时间
     */
    private Date CertificateTime;

    /**
     * 登录账号
     */
    private String LoginUserName;

    /**
     * 资格证号
     */
    private String QualificationNumber;

    /**
     * 联系电话
     */
    private String Telephone;

    /**
     * 电子邮箱
     */
    private String Email;

    /**
     * 是否取得涉外公正资格  0 是  1 否
     */
    private Integer IsNationals;

    /**
     * 是否取得涉外公正资格时间
     */
    private Date IsNationalsTime;

    /**
     * 区域Id
     */
    private Integer RegionId;

    /**
     * 工作人员类别 0 公证员 1 律师 2 人民仲裁员 3 法律服务人员 4 司法鉴定员 5 司法所人员 6 行政机关人员
     */
    private Integer WorkerType;
    
    /**
     * 所属机构名称
     */
    private String DepartmentName;
    
    /**
     * 融云返回的注册人员token
     */
    private String rytoken;


    /**
     * 专业
     */
    private String profession;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }



    public String getRytoken() {
		return rytoken;
	}

	public void setRytoken(String rytoken) {
		this.rytoken = rytoken;
	}

	public Integer getFairWorkerId() {
        return FairWorkerId;
    }

    public void setFairWorkerId(Integer FairWorkerId) {
        this.FairWorkerId = FairWorkerId;
    }
    public String getWorkerName() {
        return WorkerName;
    }

    public void setWorkerName(String WorkerName) {
        this.WorkerName = WorkerName;
    }
    public String getPinYin() {
        return PinYin;
    }

    public void setPinYin(String PinYin) {
        this.PinYin = PinYin;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }
    public String getPoliticalOutlook() {
        return PoliticalOutlook;
    }

    public void setPoliticalOutlook(String PoliticalOutlook) {
        this.PoliticalOutlook = PoliticalOutlook;
    }
    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String Speciality) {
        this.Speciality = Speciality;
    }
    public String getSFZH() {
        return SFZH;
    }

    public void setSFZH(String SFZH) {
        this.SFZH = SFZH;
    }
    public String getMZ() {
        return MZ;
    }

    public void setMZ(String MZ) {
        this.MZ = MZ;
    }
    public String getEducation() {
        return Education;
    }

    public void setEducation(String Education) {
        this.Education = Education;
    }
    public String getAcademic() {
        return Academic;
    }

    public void setAcademic(String Academic) {
        this.Academic = Academic;
    }
    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer DepartmentId) {
        this.DepartmentId = DepartmentId;
    }
    public String getProfessional() {
        return Professional;
    }

    public void setProfessional(String Professional) {
        this.Professional = Professional;
    }
    public String getCertificate() {
        return Certificate;
    }

    public void setCertificate(String Certificate) {
        this.Certificate = Certificate;
    }
    public String getPost() {
        return Post;
    }

    public void setPost(String Post) {
        this.Post = Post;
    }
    public Date getCertificateTime() {
        return CertificateTime;
    }

    public void setCertificateTime(Date CertificateTime) {
        this.CertificateTime = CertificateTime;
    }
    public String getLoginUserName() {
        return LoginUserName;
    }

    public void setLoginUserName(String LoginUserName) {
        this.LoginUserName = LoginUserName;
    }
    public String getQualificationNumber() {
        return QualificationNumber;
    }

    public void setQualificationNumber(String QualificationNumber) {
        this.QualificationNumber = QualificationNumber;
    }
    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public Integer getIsNationals() {
        return IsNationals;
    }

    public void setIsNationals(Integer IsNationals) {
        this.IsNationals = IsNationals;
    }
    public Date getIsNationalsTime() {
        return IsNationalsTime;
    }

    public void setIsNationalsTime(Date IsNationalsTime) {
        this.IsNationalsTime = IsNationalsTime;
    }
    public Integer getRegionId() {
        return RegionId;
    }

    public void setRegionId(Integer RegionId) {
        this.RegionId = RegionId;
    }
    public Integer getWorkerType() {
        return WorkerType;
    }

    public void setWorkerType(Integer WorkerType) {
        this.WorkerType = WorkerType;
    }

    public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

    @Override
    protected Serializable pkVal() {
        return this.FairWorkerId;
    }

    @Override
    public String toString() {
        return "Fairworker{" +
        "FairWorkerId=" + FairWorkerId +
        ", WorkerName=" + WorkerName +
        ", PinYin=" + PinYin +
        ", Phone=" + Phone +
        ", Image=" + Image +
        ", Sex=" + Sex +
        ", PoliticalOutlook=" + PoliticalOutlook +
        ", Speciality=" + Speciality +
        ", SFZH=" + SFZH +
        ", MZ=" + MZ +
        ", Education=" + Education +
        ", Academic=" + Academic +
        ", DepartmentId=" + DepartmentId +
        ", Professional=" + Professional +
        ", Certificate=" + Certificate +
        ", Post=" + Post +
        ", CertificateTime=" + CertificateTime +
        ", LoginUserName=" + LoginUserName +
        ", QualificationNumber=" + QualificationNumber +
        ", Telephone=" + Telephone +
        ", Email=" + Email +
        ", IsNationals=" + IsNationals +
        ", IsNationalsTime=" + IsNationalsTime +
        ", RegionId=" + RegionId +
        ", WorkerType=" + WorkerType +
        ", DepartmentName=" + DepartmentName +
        ", sexText=" + sexText +
        "}";
    }
}
