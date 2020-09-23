package com.xzx.dao;

import com.xzx.model.Register;
import com.xzx.viewModel.FingersInfo;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface RegisterMapper extends BaseMapper<Register> {
    
	@Insert({ "insert into register(RealName, SFZH, Telephone, Sex,Email,QQ,Address,City,PostalCode,Birthday,Education,WorkUnit,RegisterIP,ReGisterDate,LoginState,Occupation,MZ,CardType,SZD,NowSZD,LastLoginTime,password,Image,openid) "
    		+ "values(#{RealName}, #{SFZH}, #{Telephone}, #{Sex},#{Email},#{QQ},#{Address},#{City},#{PostalCode},#{Birthday},#{Education},#{WorkUnit},#{RegisterIP},now(),0,#{Occupation},#{MZ},#{CardType},#{SZD},#{NowSZD},now(),#{password},#{Image},#{openid})" })
	/*@Insert({ "insert into register(RealName, SFZH, Telephone,Address,PostalCode,WorkUnit,ReGisterDate,LoginState,LastLoginTime,password) "
    		+ "values(#{RealName}, #{SFZH}, #{Telephone},#{Address},#{PostalCode},#{WorkUnit},now(),0,now(),#{password})" })*/
	@Options(useGeneratedKeys = true, keyProperty = "UserId")
    int registerUser(Register register);
	
	int updateRegisterUser(Register register);
	
	@Select("select * from register where SFZH=#{sfzh} and LoginState=0")
	Register getRegisterUserBySFZH(@Param("sfzh")String SFZH);
	
	@Update("update register set LastLoginTime=now() where SFZH=#{sfzh}")
	int updateLoginTime(@Param("sfzh") String SFZH);
	
	@Select("select count(1) from register where Telephone=#{phone}")
	int registerCountByPhone(@Param("phone") String phone);
	
	@Select("select * from register where LoginState=0 and (Telephone=#{logName} or loginzh=#{logName}) and password=#{password}")
	Register appLogin(@Param("logName") String logName,@Param("password") String password);
	
	@Select("select count(1) from register where UserId=#{userId} and password=#{password} and LoginState=0")
	int testUserPassword(@Param("userId") String userId,@Param("password") String password);
	
	@Update("update register set password=#{newPassword} where UserId=#{userId}")
	int updatePassword(@Param("userId") String userId,@Param("newPassword") String newPassword);
	
	@Select("select * from register where UserId=#{registerId} and LoginState=0")
	Register getRegisterUserById(@Param("registerId")String registerId);
	
	List<FingersInfo> getAllFingerprint();
	
	int updateRegisterFinger(Register register);
	
	int updateRegisterFingerById(@Param("photoId") String photoId,@Param("registerId") String registerId);

	@Select("select * from register where openid=#{openId}")
	Register getRegisterByOpenId(@Param("openId")String openId);
	
	@Update("update register set openid=#{openid} where SFZH=#{SFZH}")
	int updateOpenId(@Param("openid") String openid,@Param("SFZH") String SFZH);
	
	@Update("update register set rytoken=#{ryToken},ryuserid=#{ryUserId} where Telephone=#{Telephone}")
	int updateRYInfo(@Param("Telephone") String Telephone,@Param("ryToken") String ryToken,@Param("ryUserId") String ryUserId);

	@Select("SELECT RealName,SFZH,Telephone,Image,Address FROM `register` where register.UserId=#{registerId}")
	Register getRegisterInfoById(@Param("registerId")String registerId);
	
	@Select("SELECT RealName FROM `register` where register.UserId=#{registerId}")
	Register getRegisterById(@Param("registerId")String registerId);
	
	@Select("SELECT UserId,RealName,Telephone,Image FROM `register` where FIND_IN_SET(register.UserId,#{registerIds})")
	List<Register> getRegisterListById(@Param("registerIds")String registerIds);
	
	@Update("update register set password=#{password} where Telephone=#{Telephone}")
	int resetPassword(@Param("password") String password,@Param("Telephone")String Telephone);

	@Insert({ "insert into register(realname,sfzh,telephone,address,nowszd,image,fingers,sex,MZ,Birthday,loginstate,registerdate,lastlogintime,isdb) "
			+ "values(#{RealName}, #{SFZH}, #{Telephone}, #{Address}, #{NowSZD}, #{Image}, #{fingers}, #{Sex}, #{MZ}, #{Birthday}, #{LoginState}, #{ReGisterDate}, #{LastLoginTime}, #{isdb})" })
	@Options(useGeneratedKeys=true, keyProperty="UserId", keyColumn="UserId")
	int insertZcdwInfo(Register register);

	@Insert({ "insert into register(realname,sfzh,telephone,address,nowszd,sex,MZ,Birthday,loginstate,registerdate,lastlogintime,isdb) "
			+ "values(#{RealName}, #{SFZH}, #{Telephone}, #{Address}, #{NowSZD}, #{Sex}, #{MZ}, #{Birthday}, #{LoginState}, #{ReGisterDate}, #{LastLoginTime}, #{isdb})" })
	@Options(useGeneratedKeys=true, keyProperty="UserId", keyColumn="UserId")
	int insertZcdwInfoOther(Register register);
}
