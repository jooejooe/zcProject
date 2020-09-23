package com.xzx.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.model.Improveability;
import com.xzx.service.IImproveabilityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-05-16
 */
@RestController
@RequestMapping("/improveAbility")
@Api(value="能力提高相关接口",description="improveAbility")
public class ImproveabilityController {
	@Autowired
	IImproveabilityService improveabilityService;
	
	/**
	 * 获取相应能力提高列表
	 * @param pageindex
	 * @param pagesize
	 * @param docType
	 * @param title
	 * @return
	 */
	@GetMapping("getImproveAbilityList")
	@ApiOperation(value="获取相应能力提高列表",notes="获取相应能力提高列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page",value="当前页码",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="limit",value="每页条数",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="docType",value="资料类别【-1-全部，0-文档，1-视频】",dataType="String",required=true,paramType="query",allowableValues="-1,0,1"),
		@ApiImplicitParam(name="title",value="资料标题",dataType="String",required=false,paramType="query")
	})
	public JSONObject getImproveAbilityList(@RequestParam("page")String pageindex,@RequestParam("limit")String pagesize,@RequestParam("docType")String docType,String title)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			PageHelper.startPage(Integer.parseInt(pageindex),Integer.parseInt(pagesize));

			List<Improveability> list=improveabilityService.getImproveAbilityDocList(docType, title);

			PageInfo<Improveability> page = new PageInfo<Improveability>(list);

			jsonObject.put("data", page.getList());
			jsonObject.put("code", "0");
			jsonObject.put("Message", "获取能力提高列表成功！");
			jsonObject.put("count", page.getTotal());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("code", "1");
			jsonObject.put("Msg", "获取能力提高列表失败！");
		}
		return jsonObject;
	}
	
	@PostMapping("addImproveAbility")
	@ApiOperation(value="添加能力提高文档",notes="添加能力提高文档",produces="application/json")
	public JSONObject addImproveAbility(@RequestBody @ApiParam(value = "能力提高信息")Improveability improveability)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			int existCount=improveabilityService.getTitleCount(improveability.getTitle(), -1);
			
			if(existCount>0)
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "当前标题已存在！");
			}
			else
			{
				if(improveabilityService.addImproveAbility(improveability)>0)
				{
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "添加能力提高文档成功！");
				}
				else 
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "添加能力提高文档失败！");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "添加能力提高文档失败！");
		}

		return jsonObject;
	}
	
	/**
	 * 修改能力提高文档
	 * @param improveability
	 * @return
	 */
	@PostMapping("updateImproveAbility")
	@ApiOperation(value="修改能力提高文档【修改】",notes="修改能力提高文档【修改】",produces="application/json")
	public JSONObject updateImproveAbility(@RequestBody @ApiParam(value = "能力提高信息")Improveability improveability)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			int existCount=improveabilityService.getTitleCount(improveability.getTitle(), improveability.getId());
			
			if(existCount>0)
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "当前标题已存在！");
			}
			else 
			{
				if(improveabilityService.updateImproveAbility(improveability)>0)
				{
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "修改能力提高文档成功！");
				}
				else 
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "修改能力提高文档失败！");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "修改能力提高文档失败！");
		}

		return jsonObject;
	}
	
	/**
	 * 获取相应能力提高详情
	 * @param docId
	 * @return
	 */
	@GetMapping("getImproveAbilityInfo")
	@ApiOperation(value="获取相应能力提高详情",notes="获取相应能力提高详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="docId",value="资料id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getImproveAbilityInfo(@RequestParam("docId")String docId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Improveability improveability=improveabilityService.getInfoById(docId);

			jsonObject.put("data", improveability);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取相应能力提高详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取相应能力提高详情失败！");
		}
		return jsonObject;
	}
	
	@PostMapping("delImproveAbility")
	@ApiOperation(value="删除文档",notes="删除文档",produces="application/json")
	public JSONObject delImproveAbility(@RequestBody @ApiParam(value = "能力提高信息")Improveability improveability)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(improveabilityService.delImproveAbility(improveability)>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "删除能力提高文档成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "删除能力提高文档失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "删除能力提高文档失败！");
		}

		return jsonObject;
	}
}
