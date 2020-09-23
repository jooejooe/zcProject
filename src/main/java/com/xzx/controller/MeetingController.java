package com.xzx.controller;


import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.UserLoginToken;
import com.xzx.model.Meeting;
import com.xzx.service.IMeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/meeting")
@Api(value="手机端会议室相关接口",description="meeting")
public class MeetingController {

	@Autowired
    IMeetingService meetingService;
	
	@UserLoginToken
	@GetMapping("getMeetingList")
	@ApiOperation(value="app端获取可用会议室列表",notes="app端获取可用会议室列表",produces="application/json")
	public JSONObject getMeetingList()
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{				
			List<Meeting> list=meetingService.getMeetingList();
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "app端获取可用会议室列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "app端获取可用会议室列表失败！");
		}
		return jsonObject;
	}
}
