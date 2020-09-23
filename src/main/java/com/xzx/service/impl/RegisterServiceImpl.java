package com.xzx.service.impl;

import com.xzx.model.Register;
import com.xzx.dao.FingerprintinfosMapper;
import com.xzx.dao.RegisterMapper;
import com.xzx.dao.SocialMapper;
import com.xzx.service.IRegisterService;
import com.xzx.viewModel.FingersInfo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements IRegisterService {
    
	@Autowired
	private RegisterMapper registerMapper;
	
	@Autowired
	private FingerprintinfosMapper fingerprintinfosMapper;
	
	@Autowired
	private SocialMapper socialMapper;
	
	public int registerUser(Register register)
    {
		return registerMapper.registerUser(register);
    }
	
	public int updateRegisterUser(Register register)
	{
		return registerMapper.updateRegisterUser(register);
	}
	
	public Register getRegisterUserBySFZH(String SFZH)
	{
		return registerMapper.getRegisterUserBySFZH(SFZH);
	}
	
	public int updateLoginTime(String SFZH)
	{
		return registerMapper.updateLoginTime(SFZH);
	}
	
	public int registerCountByPhone(String phone)
	{
		return registerMapper.registerCountByPhone(phone);
	}
	
	public Register appLogin(String logName,String password)
	{
		return registerMapper.appLogin(logName, password);
	}
	
	public int testUserPassword(String userId,String password)
	{
		return registerMapper.testUserPassword(userId, password);
	}
	
	public int updatePassword(String userId,String newPassword)
	{
		return registerMapper.updatePassword(userId, newPassword);
	}
	
	public Register getRegisterUserById(String registerId)
	{
		return registerMapper.getRegisterUserById(registerId);
	}
	
	public List<FingersInfo> getAllFingerprint()
	{
		return registerMapper.getAllFingerprint();
	}
	
	@Transactional
	public int updateRegisterFingerById(String photoId,String registerId,String SFZH)
	{
		int flag=0;
		
		int flag1=0;
		int flag2=0;
		int flag3=0;
		
		//更改注册用户指纹记录
		flag1=registerMapper.updateRegisterFingerById(photoId, registerId);
		
		//更改社矫人员指纹记录
		flag2=socialMapper.updateSocialFingerById(photoId, SFZH);
		
		//删除临时指纹数据
		flag3=fingerprintinfosMapper.delFingerInfo(Integer.parseInt(photoId));
		
		if(flag1>0&&flag2>=0&&flag3>0)
			flag=1;
		else
			flag=0;
		
		return flag;
	}
	
	public Register getRegisterByOpenId(String openId)
	{
		return registerMapper.getRegisterByOpenId(openId);
	}
	
	public int updateOpenId(String openid,String SFZH)
	{
		return registerMapper.updateOpenId(openid, SFZH);
	}
	
	public int updateRYInfo(String Telephone,String ryToken,String ryUserId)
	{
		return registerMapper.updateRYInfo(Telephone, ryToken, ryUserId);
	}
	
	public Register getRegisterInfoById(String registerId)
	{
		return registerMapper.getRegisterInfoById(registerId);
	}
	
	public Register getRegisterById(String registerId)
	{
		return registerMapper.getRegisterById(registerId);
	}
	
	public List<Register> getRegisterListById(String registerIds)
	{
		return registerMapper.getRegisterListById(registerIds);
	}
	
	public int resetPassword(String password,String Telephone)
	{
		return registerMapper.resetPassword(password, Telephone);
	}
	public int insertInfo(Register register)
	{
		return registerMapper.insertZcdwInfo(register);
	}
	public int insertInfoOther(Register register)
	{
		return registerMapper.insertZcdwInfoOther(register);
	}
}