package com.xzx.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsResultBase;
import com.github.qcloudsms.SmsSingleSender;
import com.xzx.viewModel.MessageSendInfo;

@Component
public class MessageSendUtil {
	
	public static int AppId;
	
	private static String AppKey;
	
	private static String Sign;
	
	@Value("${messageSend.AppId}")
	public void setAppId(int appId) {
		AppId = appId;
	}

	@Value("${messageSend.AppKey}")
	public void setAppKey(String appKey) {
		AppKey = appKey;
	}


	@Value("${messageSend.Sign}")
	public void setSign(String sign) {
		Sign = sign;
	}
	
    /**
     * 按模板发送短信 支持单发和群发
     * @param isSingle 是否单发 true: 单发，false: 群发
     * @param form 需要发送的短信内容及收信人手机号
     * @param config 短信配置
     * @throws CustomException 发送失败时捕获的异常信息
     */
    public static SmsResultBase sendMessage(MessageSendInfo messageInfo) {
        String regex = ",";
        String[] params = messageInfo.getParams().split(regex);
        String[] phones = messageInfo.getTelephones().split(regex);
        
        int templateId=messageInfo.getTemplateId();
        
        SmsResultBase result=null;

        try {
            // 是否单发
            if (messageInfo.getIsSingle()) 
            {
                SmsSingleSender ssender = new SmsSingleSender(AppId,AppKey);
                result= ssender.sendWithParam("86", phones[0], templateId, params, Sign, "", "");
            } 
            else 
            {
                SmsMultiSender msender = new SmsMultiSender(AppId, AppKey);
                result=msender.sendWithParam("86", phones, templateId, params, Sign, "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
