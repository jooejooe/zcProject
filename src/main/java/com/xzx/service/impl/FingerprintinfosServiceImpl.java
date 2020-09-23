package com.xzx.service.impl;

import com.xzx.model.Fingerprintinfos;
import com.xzx.model.Register;
import com.xzx.model.Social;
import com.xzx.dao.FingerprintinfosMapper;
import com.xzx.dao.RegisterMapper;
import com.xzx.dao.SocialMapper;
import com.xzx.service.IFingerprintinfosService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-11-04
 */
@Service
public class FingerprintinfosServiceImpl extends ServiceImpl<FingerprintinfosMapper, Fingerprintinfos> implements IFingerprintinfosService {
	@Autowired
	FingerprintinfosMapper fingerprintinfosMapper;
	
	@Autowired
	RegisterMapper registerMapper;
	
	@Autowired
	SocialMapper socialMapper;
	
	@Transactional
	public int insertFingerPhoto(Fingerprintinfos fingerprintinfos,Register register,Social social)
	{
		int flag1=0;
		int flag2=0;
		int flag3=0;
		
		int flag=0;
		
		flag1=fingerprintinfosMapper.insertFingerPhoto(fingerprintinfos);
		flag2=registerMapper.updateRegisterFinger(register);
		flag3=socialMapper.updateSocialFinger(social);
		
		if(flag1>0&&flag2>=0&&flag3>=0)
			flag=1;
		else
			flag=0;
		
		return flag;
	}	
	
	public Fingerprintinfos getFingerById(int id)
	{
		return fingerprintinfosMapper.getFingerById(id);
	}
	
	public int delFingerInfo(int id)
	{
		return fingerprintinfosMapper.delFingerInfo(id);
	}
}
