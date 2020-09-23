package com.xzx.service;

import com.xzx.model.Register;
import com.xzx.viewModel.FingersInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface IRegisterService extends IService<Register> {
    int registerUser(Register register);
    
    int updateRegisterUser(Register register);
    
    Register getRegisterUserBySFZH(String SFZH);
    
    int updateLoginTime(String SFZH);
    
    int registerCountByPhone(String phone);
    
    Register appLogin(String logName,String password);
    
    int testUserPassword(String userId,String password);
    
    int updatePassword(String userId,String newPassword);
    
    Register getRegisterUserById(String registerId);
    
    List<FingersInfo> getAllFingerprint();
    
    int updateRegisterFingerById(String photoId,String registerId,String SFZH);
    
	Register getRegisterByOpenId(String openId);
	
	int updateOpenId(String openid,String SFZH);
	
	int updateRYInfo(String Telephone,String ryToken,String ryUserId);
	
	Register getRegisterInfoById(String registerId);
	
	Register getRegisterById(String registerId);
	
	List<Register> getRegisterListById(String registerIds);
	
	int resetPassword(String password,String Telephone);

    int insertInfo(Register register);
    int insertInfoOther(Register register);
}
