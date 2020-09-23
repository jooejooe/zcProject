package com.xzx.service.impl;

//import com.xzx.model.Access;
import com.xzx.model.Access_tjyupload;
//import com.xzx.model.Access_wait;
import com.xzx.model.Tjy_access;
//import com.xzx.common.Base64;
import com.xzx.dao.AccessMapper;
import com.xzx.dao.Access_tjyuploadMapper;
import com.xzx.dao.Access_waitMapper;
import com.xzx.dao.Tjy_accessMapper;
import com.xzx.service.ITjy_accessService;
import com.xzx.viewModel.TJYAccessInfo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-08-25
 */
@Service
public class Tjy_accessServiceImpl extends ServiceImpl<Tjy_accessMapper, Tjy_access> implements ITjy_accessService {
	@Autowired
	Tjy_accessMapper tjy_accessMapper;
	
	@Autowired
	Access_tjyuploadMapper access_tjyuploadMapper;
	
	@Autowired
	Access_waitMapper access_waitMapper;
	
	@Autowired
	AccessMapper accessMapper;
	
	@Transactional
	public Boolean insertTJYAccess(TJYAccessInfo tjyAccessInfo)
	{		
		int flag1=0;
		
		int flag2=0;
		
		int flag3=0;
		
		flag2=access_tjyuploadMapper.delUploadAccess(tjyAccessInfo.getFairworkerId(), tjyAccessInfo.getCaseId());
		
		flag3=tjy_accessMapper.delTJYAccess(tjyAccessInfo.getCaseId());
		
		List<Tjy_access> list=tjyAccessInfo.getList();
		
		List<Access_tjyupload> listUpload=tjyAccessInfo.getListUploadAccess();
		
		if(listUpload!=null&&listUpload.size()>0)
		{
			for(int i=0;i<listUpload.size();i++)
			{
				Access_tjyupload access_tjyupload=listUpload.get(i);
				
				access_tjyuploadMapper.addUploadAccess(access_tjyupload);
				
				int accessUploadId=access_tjyupload.getAccess_tjyId();
				
				Tjy_access tjy_access=new Tjy_access();
				tjy_access.setAnjianId(Integer.parseInt(tjyAccessInfo.getCaseId()));
				tjy_access.setAccessurl(access_tjyupload.getAccessurl());
				tjy_access.setFairworkerId(access_tjyupload.getFairworkerId());
				tjy_access.setUsertype(2);
				tjy_access.setAccessname(access_tjyupload.getAccessname());
				tjy_access.setAccessid(accessUploadId);
				
				list.add(tjy_access);
			}
		}
		
		flag1=tjy_accessMapper.insertBatchTJYAccess(tjyAccessInfo.getList());
		
		return (flag1>0&&flag2>=0&&flag3>=0)?true:false;
	}
	
	public List<Tjy_access> getTjyAccessList(@Param("caseId")String caseId,@Param("workerId")String workerId)
	{
		return tjy_accessMapper.getTjyAccessList(caseId, workerId);
	}
	
	public List<Tjy_access> getDsrTjyAccessList(String caseId,String userId)
	{
		return tjy_accessMapper.getDsrTjyAccessList(caseId, userId);
	}
	
//	@Transactional
	public Boolean addWaitTJYAccess(int accessWaitId,String serverPath,int workId) throws IOException
	{
//		int flag1=0;
		int flag2=0;
//		int flag3=0;
//		
//		Access_wait access_wait=access_waitMapper.selectById(accessWaitId);
//		
//		String accessWaitUrl=access_wait.getAccessurl();
//		
//		String ext=accessWaitUrl.substring(accessWaitUrl.lastIndexOf(".")+1);
//		
//		String base64="data:image/"+ext+";base64,"+Base64.fileToBase64(accessWaitUrl, serverPath);
//		
//		Access access=new Access();
//		access.setAccessInfo(base64);
//		access.setUserId(access_wait.getUserId());
//		access.setModelType(1);
//		access.setModelId(access_wait.getAnjianId());
//		access.setAccessName(access_wait.getAccessname());
//		access.setAccessType(1);
//		
//		flag1=accessMapper.addAccess(access);
//		
//		Integer accessId=access.getAccessId();
		
		flag2=tjy_accessMapper.addWaitTJYAccess(Integer.toString(workId), "-1", Integer.toString(accessWaitId));
		
		//flag3=access_waitMapper.delAccessWait(Integer.toString(accessWaitId));
		
		boolean flag=flag2>0;//flag1>0&&flag2>0&&flag3>0;
		
		return flag;
	}
}
