package com.xzx.service.impl;

import com.xzx.model.SendMessageLog;
import com.xzx.common.MessageSendUtil;
import com.xzx.dao.SendMessageLogMapper;
import com.xzx.service.ISendMessageLogService;
import com.xzx.viewModel.MessageSendInfo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.qcloudsms.SmsResultBase;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-10-21
 */
@Service
public class SendMessageLogServiceImpl extends ServiceImpl<SendMessageLogMapper, SendMessageLog> implements ISendMessageLogService {
	@Autowired
	SendMessageLogMapper sendMessageLogMapper;

	public JSONObject addSendMsgLog(MessageSendInfo messageSendInfo) throws JSONException
	{
		JSONObject jsonResult=new JSONObject();
		
		SmsResultBase result=MessageSendUtil.sendMessage(messageSendInfo);
		org.json.JSONObject jsonObject=result.parseToJson(result.getResponse());
		
		List<SendMessageLog> list=new ArrayList<SendMessageLog>();
		
	    int resultCode=jsonObject.getInt("result");
		if(messageSendInfo.getIsSingle())
		{
			String[] phones=messageSendInfo.getTelephones().split(",");
			
			SendMessageLog sendMessageLog=new SendMessageLog();
			
			sendMessageLog.setTemplateId(messageSendInfo.getTemplateId());//模板id
			sendMessageLog.setTelephone(phones[0]);//手机号码
			sendMessageLog.setParams(messageSendInfo.getParams());//参数
			sendMessageLog.setRegionId(messageSendInfo.getRegionId());//区域id
			sendMessageLog.setMessageType(messageSendInfo.getMessageType());
			
		    if(resultCode!=0)
			{
		    	String errMsg=jsonObject.getString("errmsg");
				
				sendMessageLog.setState(1);
				sendMessageLog.setErrorMsg(errMsg);			
			}
			else 
			{	
				sendMessageLog.setState(0);
			}
		    
		    list.add(sendMessageLog);
		}
		else
		{	
			if(resultCode==0)
			{
				org.json.JSONArray jsonArray=jsonObject.getJSONArray("detail");
				for(int i=0;i<jsonArray.length();i++)
				{
					SendMessageLog sendMessageLog=new SendMessageLog();
					
					org.json.JSONObject curJson=jsonArray.getJSONObject(i);
					String phone=curJson.getString("mobile");
					
					sendMessageLog.setTelephone(phone);//手机号码
					
					if(curJson.getInt("result")==0)
					{	
						sendMessageLog.setState(0);
					}
					else
					{
						String errMsg=curJson.getString("errmsg");
						
						sendMessageLog.setState(1);
						sendMessageLog.setErrorMsg(errMsg);	
					}
					
					sendMessageLog.setTemplateId(messageSendInfo.getTemplateId());//模板id
					sendMessageLog.setParams(messageSendInfo.getParams());//参数
					sendMessageLog.setRegionId(messageSendInfo.getRegionId());//区域id
					
					sendMessageLog.setMessageType(messageSendInfo.getMessageType());
					
					list.add(sendMessageLog);
				}
			}
		}
		
		jsonResult.put("resultCode", resultCode);
		
		if(list!=null&&list.size()>0)
			jsonResult.put("insertDatabase", sendMessageLogMapper.insertBatchSendMsgLog(list));
		else
			jsonResult.put("insertDatabase",0);
		
		return jsonResult;
	}
}
