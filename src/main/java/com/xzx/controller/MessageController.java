package com.xzx.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.annotation.UserLoginToken;
import com.xzx.model.Message;
import com.xzx.service.IMessageService;
import com.xzx.service.ISocialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-09-27
 */
@RestController
@RequestMapping("/message")
@Api(value="消息列表相关接口",description="message")
public class MessageController {
	@Autowired
    ISocialService socialService;

	@Autowired
    IMessageService messageService;

	/**
	 * 获取消息列表
	 * @param userType
	 * @param userId
	 * @param msgTitle
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getMessageList")
	@ApiOperation(value="获取消息列表",notes="根据操作用户类别和用户id获取信息列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userType",value="用户类别(0-普通用户；1-工作人员)",dataType="String",required=true,paramType="query",allowableValues="0,1"),
		@ApiImplicitParam(name="userId",value="当前用户id",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="msgTitle",value="搜索条件",dataType="String",required=false,paramType="query",defaultValue=""),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getMessageList(
			@RequestParam("userType") String userType,
			@RequestParam("userId") String userId,
			String msgTitle,
			String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String SendUserTypeStr="";
			//当用户类别为普通用户时，判断当前用户是否是社矫人员
			if(userType.equals("0"))
			{
				SendUserTypeStr="0";

				int socialCount=socialService.getSocialByUserId(userId);

				if(socialCount>0)
					SendUserTypeStr+=",2";

				SendUserTypeStr+=",3";
			}
			else		
				SendUserTypeStr="1,3";
			
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				
				PageHelper.startPage(pageindex,pageSize);		
				List<Message> list=messageService.getMessageByParams(userId, SendUserTypeStr, userType,msgTitle);
				PageInfo<Message> page = new PageInfo<Message>(list);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取消息分页列表成功！");
			}
			else 
			{
				List<Message> list=messageService.getMessageByParams(userId, SendUserTypeStr, userType,msgTitle);
				
				jsonObject.put("data", list);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取消息列表成功！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取消息列表失败！");
		}

		return jsonObject;
	}

	/**
	 * app根据消息id获取消息详情
	 * @param msgId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getMsgById")
	@ApiOperation(value="获取相应消息",notes="根据相应消息id获取相应消息内容",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="msgId",value="消息id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getMessageInfo(
			@RequestParam("msgId") String msgId
			){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Message message=messageService.getMessageInfo(msgId);
			
			jsonObject.put("data", message);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取消息信息成功！");
		}
		catch(Exception e)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取消息信息失败！");
		}

		return jsonObject;
	}
}
