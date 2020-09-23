package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.UserLoginToken;
import com.xzx.service.IJustService;
import com.xzx.service.ISpeedService;
import com.xzx.viewModel.JustInfos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prove")
@Api(value="公证办理业务相关api",description="prove")
public class ProveController {

	@Autowired
	private IJustService justService;
	
	@Autowired
    ISpeedService speedService;

	/**
	 * 添加公证办理
	 * @param justInfos
	 * @return
	 */
	@UserLoginToken
	@PostMapping("addProveItem")
	@ApiOperation(value="【公证办理】添加公证办理",notes="添加公证办理",produces="application/json")
	public JSONObject addProveItem(@RequestBody @ApiParam(value = "公证办理相关信息") JustInfos justInfos)
	{
		JSONObject jsonObject = new JSONObject();
		try 
		{
			int userId=justInfos.getJust().getUserId();
			
			int existCount=justService.getUseCount(userId, Integer.toString(justInfos.getJust().getBiddingMattersId()));//speedService.existSpeed("0", Integer.toString(userId), "0,1,3");
			
			if(existCount==0)
			{
				boolean flag=justService.insertJust(justInfos.getJust(), justInfos.getListAccess());
				if(flag)
				{
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "公证办理添加成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "公证办理添加失败！");
				}
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "已申办过此类业务！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "公证办理添加失败！");
		}
		return jsonObject;
	}
	
	@UserLoginToken
	@GetMapping("getOnlineIssue")
	@ApiOperation(value="获取已办此类业务数量",notes="获取已办此类业务",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "modelType", value = "业务类型【0 公证办理 1 人民调解 2 司法鉴定 3 法律援助 4 预约公证办理 5  预约人民调解 6 预约司法鉴定 7 预约法律援助 8 预约律师】", dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query")
	})
	public JSONObject getOnlineIssue(@RequestParam("modelType")String modelType,@RequestParam("userId")String userId){
		JSONObject jsonObject = new JSONObject();
		
		try 
		{
			String countStates="";
			if("0,1,2,3".contains(modelType))
			{
				countStates="0,1,3";//待审批，审批中，补充材料
			}
			else
			{
				countStates="0,3,4";//待确定，更改预约时间，补充材料
			}
			
			int existCount=speedService.existSpeed(modelType, userId, countStates);
			
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取已办此类业务数量成功！");
			jsonObject.put("existCount", existCount);
		}
		catch(Exception e)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取已办此类业务数量失败！");
		}
		
		return jsonObject;
	}
}