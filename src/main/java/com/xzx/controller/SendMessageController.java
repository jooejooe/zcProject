package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.UserLoginToken;
import com.xzx.model.MessageTemplate;
import com.xzx.service.IMessageTemplateService;
import com.xzx.service.IRegionService;
import com.xzx.service.ISendMessageLogService;
import com.xzx.viewModel.MessageSendInfo;
import io.swagger.annotations.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/sendMessage")
@Api(value="短信推送接口")
public class SendMessageController {
	@Autowired
    ISendMessageLogService sendMessageLogService;
	
	@Autowired
    IRegionService regionService;
	
	@Autowired
    IMessageTemplateService messageTemplateService;
	
	/**
	 * 通知短信发送接口
	 * @return
	 * @throws JSONException
	 */
	@UserLoginToken
	@PostMapping("messageSend")
	@ApiOperation(value="短信发送",notes="短信发送："
			+ "<br/>telephones:要发送短信的电话号码拼接串（英文逗号分隔）"
			+ "<br/>params:发送短信的参数集合"
			+ "<br/>isSingle:（单发）true/（群发）false"
			+ "<br/>regionIdOrToken:终端机请求设备token,app请求工作人员所属区域id"
			+ "<br/>state:事项当前状态值（在线办理：0 待审批  1 审批中 2 审核通过 3 补充材料 4 已撤销 5 未通过;预约：0 待确定 1 预约成功 2 预约失败 3 更改预约时间）"
			+ "<br/>workerType:短信接收者用户类型(0-普通用户；1-工作人员)"
			+ "</br>valueType:regionIdOrToken传入值是token还是区域regionId（0-token;1-regionId）"
			+ "</br>itemType:在线办理还是预约（0-在线办理；1-预约）"
			+ "</br>modelType:业务类型【预约：0：公证办理预约，1：人民调解预约，2：司法鉴定预约，3：法律援助预约，4：律师预约；在线：0：公证办理，1：人民调解，2：司法鉴定，3：法律援助】",
			produces="application/json")
	public JSONObject messageSend(@RequestBody @ApiParam(value = "短信发送相关信息") MessageSendInfo messageSendInfo) throws JSONException
	{	
		JSONObject jsonObject=new JSONObject();
		try {
			
			int regionId=regionService.getTopRegionId(messageSendInfo.getRegionIdOrToken(), messageSendInfo.getValueType());			
			
			MessageTemplate messageTemplate=messageTemplateService.getTemplateInfo(regionId, messageSendInfo.getState(), messageSendInfo.getWorkerType(),messageSendInfo.getItemType(),messageSendInfo.getModelType());
			
			if(messageTemplate!=null)
			{
				if(messageTemplate.getParamsCount()==messageSendInfo.getParams().split(",").length)
				{
					messageSendInfo.setTemplateId(messageTemplate.getTemplateId());
					messageSendInfo.setRegionId(regionId);
					
					messageSendInfo.setMessageType(0);
					
					JSONObject jsonResult=sendMessageLogService.addSendMsgLog(messageSendInfo);
					
					if(Integer.parseInt(jsonResult.getString("resultCode"))==0&&Integer.parseInt(jsonResult.getString("insertDatabase"))>0)
					{
						jsonObject.put("Code", "00000000");
						jsonObject.put("Msg", "短信发送成功！");
					}
					else
					{
						jsonObject.put("Code", "11111111");
						jsonObject.put("Msg", "短信发送失败！");
					}
				}
				else 
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "短信传入参数错误！");
				}
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "短信模板不存在！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "短信发送失败！");
		}
		
		return jsonObject;
	}
	
	/**
	 * 获取手机短信验证码
	 * @param phone
	 * @param time
	 * @param workerType
	 * @param sign
	 * @return
	 * @throws JSONException
	 */
	@GetMapping("getVerifyCode")
	@ApiOperation(value="获取短信验证码",notes="获取验证码",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="phone",value="手机号码",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="workerType",value="人员类型(0:普通用户,1:工作人员)",dataType="String",required=true,paramType="query",allowableValues="0,1"),
		@ApiImplicitParam(name="token",value="token值",dataType="String",required=true,paramType="query")
	})
	public JSONObject getVerifyCode(@RequestParam("phone") String phone,@RequestParam("workerType") String workerType,@RequestParam("token") String token) throws JSONException
	{
		JSONObject jsonObject=new JSONObject();
		
		try {
			/*生成6位验证码start*/
			String code = "";
			Random random = new Random();
			for (int i = 0; i < 6; i++) {
				int r = random.nextInt(10); //每次随机出一个数字（0-9）
				code = code + r;  //把每次随机出的数字拼在一起
			}
			/*生成6位验证码end*/
			
			int regionId=regionService.getTopRegionId(token, 0);			
			
			int templateId=messageTemplateService.getVerifyTemplateId(workerType,regionId);
			
			MessageSendInfo messageSendInfo=new MessageSendInfo();
			messageSendInfo.setIsSingle(true);
			//messageSendInfo.setParams(code+","+time);
			messageSendInfo.setParams(code);
			/*messageSendInfo.setSign(sign);*/
			messageSendInfo.setTelephones(phone);
			messageSendInfo.setTemplateId(templateId);
			messageSendInfo.setWorkerType(Integer.parseInt(workerType));
			messageSendInfo.setMessageType(1);
			
			JSONObject jsonResult=sendMessageLogService.addSendMsgLog(messageSendInfo);
			
			/*			if(true)
			{
				jsonObject.put("VerifyCode", code);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "验证码发送成功！");
			}*/
			if(Integer.parseInt(jsonResult.getString("resultCode"))==0&&Integer.parseInt(jsonResult.getString("insertDatabase"))>0)
			{
				jsonObject.put("VerifyCode", code);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "验证码发送成功！");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "验证码发送失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "验证码发送失败！");
		}	
		
		return jsonObject;
	}
}
