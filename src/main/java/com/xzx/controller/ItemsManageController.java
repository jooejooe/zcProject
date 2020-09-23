package com.xzx.controller;


import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.UserLoginToken;
import com.xzx.model.Lawparent;
import com.xzx.model.Lawparentnext;
import com.xzx.service.*;
import com.xzx.viewModel.ItemsManageInfos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/itemManage")
@Api(value="在线办理（人民调解，司法鉴定，法律援助）相关api【0 公证 1 人民调解 2 司法鉴定 3 法律援助】",description="itemManage")
public class ItemsManageController {
	@Autowired
    IAuthenticService authenticService;
	
	@Autowired
    ISpeedService speedService;
	
	@Autowired
    ILawparentService lawparentService;
	
	@Autowired
    ILawparentnextService lawparentnextService;
	
	@Autowired
    IAppraisalService appraisalService;
	
	@Autowired
    ILawhelpService lawhelpService;
	
	/**
	 * 在线添加【人民调解、法律援助、律师预约】
	 * @param itemsManageInfos
	 * @return
	 */
	@UserLoginToken
	@PostMapping("addItemByType")
	@ApiOperation(value="[在线办理]添加事项",notes="添加事项",produces="application/json")
	public JSONObject addItemByType(@RequestBody @ApiParam(value = "业务办理相关信息") ItemsManageInfos itemsManageInfos)
	{
		JSONObject jsonObject = new JSONObject();
		try 
		{
			String itemType=itemsManageInfos.getType();//办理类别(1 人民调解 2 司法鉴定 3 法律援助)
			int userId=Integer.parseInt(itemsManageInfos.getUserId());//itemsManageInfos.getRegister().getUserId();
			
			int existCount=0;//speedService.existSpeed(itemType, Integer.toString(userId), "0,1,3");
			
			if(itemType.equals("1"))
			{
				existCount=authenticService.getUseCount(Integer.toString(userId), Integer.toString(itemsManageInfos.getAuthentic().getAssistanceId()));
			}
			else if(itemType.equals("2"))
			{
				existCount=appraisalService.getUseCount(Integer.toString(userId), Integer.toString(itemsManageInfos.getAppraisal().getMatterType()));
			}
			else if(itemType.equals("3"))
			{
				existCount=lawhelpService.getUseCount(Integer.toString(userId), Integer.toString(itemsManageInfos.getLawhelp().getAssistanceId()));
			}
			
			if(existCount==0)
			{
				boolean flag=authenticService.insertAuthentic(itemsManageInfos);
				
				if(flag)
				{
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "业务申办成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "业务申办失败！");
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
			jsonObject.put("Msg", "业务申办失败！");
		}
		return jsonObject;
	}
	
	/**
	 * 根据法律援助类别获取相应的法律援助待选题目列表
	 * @param assistanceId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getQuesByType")
	@ApiOperation(value="[法律援助]根据法律援助类别获取相应的法律援助待选题目列表",notes="根据法律援助类别获取相应的法律援助待选题目列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "assistanceId", value = "法律援助类别id", dataType = "String", paramType = "query")
	})
	public JSONObject getQuesByType(@RequestParam("assistanceId") String assistanceId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Lawparent> list=lawparentService.getQuestionsByType(assistanceId);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取题目列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取题目列表失败！");
		}		

		return jsonObject;
	}
	
	/**
	 * 根据法律援助题目id获取相应的待选选项列表
	 * @param typeId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getOptionsByType")
	@ApiOperation(value="[法律援助]根据法律援助题目id获取相应的待选选项列表",notes="根据法律援助题目id获取相应的待选选项列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "法律援助相应类别相应题目id", dataType = "String", paramType = "query")
	})
	public JSONObject getOptionsByType(@RequestParam("typeId") String typeId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Lawparentnext> list=lawparentnextService.getOptionsById(typeId);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取选项列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取选项列表失败！");
		}		

		return jsonObject;
	}
}
