package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.OperationLogDetail;
import com.xzx.annotation.UserLoginToken;
import com.xzx.enums.OperationType;
import com.xzx.enums.OperationUnit;
import com.xzx.service.ISpeedService;
import com.xzx.service.ISubscribeService;
import com.xzx.service.ISubscribetypeService;
import com.xzx.viewModel.SubscribeInfos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
@RequestMapping("/subscribe")
@RestController
@Api(value="预约业务相关api",description="subscribe")
public class SubscribeController {
	@Autowired
    ISubscribeService subscribeService;

	@Autowired
    ISpeedService speedService;

	@Autowired
    ISubscribetypeService subscribetypeService;

	/**
	 * 添加预约事项
	 * @param subscribeInfos
	 * @return
	 */
	@UserLoginToken
	@PostMapping("addItemByType")
	@ApiOperation(value="[在线预约]添加事项(预约的具体业务 0 公证 1 人民调解 2司法鉴定 3 法律援助 4 律师预约)",notes="添加预约事项",produces="application/json")
	public JSONObject addSubscribe(@RequestBody @ApiParam(value = "在线预约相关信息") SubscribeInfos subscribeInfos)
	{
		JSONObject jsonObject = new JSONObject();
		try 
		{
			//String itemType=Integer.toString(subscribeInfos.getSubscribe().getModelType()+4);
			int userId=subscribeInfos.getSubscribe().getUserId();

			int existCount=subscribeService.getUseCount(userId, subscribeInfos.getSubscribe().getSubscribeType(), subscribeInfos.getSubscribe().getModelType(), subscribeInfos.getSubscribe().getModelType()+4);//speedService.existSpeed(itemType, Integer.toString(userId), "0,3");

			if(existCount==0)
			{
				boolean flag=subscribeService.addSubscribe(subscribeInfos);

				if(flag)
				{
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "在线预约成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "在线预约失败！");
				}
			}
			else
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "已申办过此类业务！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "业务申办失败！");
		}
		return jsonObject;
	}

	/**
	 * 获取服务端当前日期
	 * @return
	 */
	@OperationLogDetail(detail = "获取服务端当前日期",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
	@GetMapping("getCurDate")
	@ApiOperation(value="获取当前日期",notes="获取当前日期",produces="application/json")
	public JSONObject getCurDate(){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateNow=df.format(new Date());

			jsonObject.put("currentDate", dateNow);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取服务端当前日期成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取服务端当前日期失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取预约类型
	 * @return
	 */
	//@OperationLogDetail(detail = "获取预约类型",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
	@UserLoginToken
	@GetMapping("getSubscribeType")
	@ApiOperation(value="获取预约类型",notes="获取预约类型",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value="用户id",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="subscribeModelType",value="预约业务类型(0 公证 1 人民调解 2司法鉴定 3 法律援助 4 律师预约)",dataType="String",required=false,paramType="query",allowableValues="0,1,2,3,4")
	})
	public JSONObject getSubscribeType(@RequestParam("userId") String userId,@RequestParam("subscribeModelType") String subscribeModelType){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Map<String, String>> list=subscribetypeService.getSubscribeType(userId,subscribeModelType,Integer.toString(Integer.parseInt(subscribeModelType)+4));

			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取预约类型成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取预约类型失败！");
		}		

		return jsonObject;
	}
}
