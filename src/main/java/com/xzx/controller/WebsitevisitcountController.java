package com.xzx.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xzx.model.Websitevisitcount;
import com.xzx.service.IWebsitevisitcountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Helen
 * @since 2020-02-13
 */

@RestController
@RequestMapping("/websitevisitcount")
@Api(value="网站访问量相关接口",description="websitevisitcount")
public class WebsitevisitcountController {
	@Autowired
	IWebsitevisitcountService websitevisitcountService;
	
	/**
	 * 获取网站访问量相关数据
	 * @return
	 */
	@GetMapping("getWebsiteVisitInfo")
	@ApiOperation(value="获取网站访问量相关数据",notes="获取网站访问量相关数据"
			+ "<br/>count1:网站每日访问量"
			+ "<br/>count2:网站访问总量"
			+ "<br/>count3:网站季度访问量"
			+ "<br/>count4:网站月度访问量"
			+ "<br/>count5:备用字段1"
			+ "<br/>count6:备用字段2",
			produces="application/json")
	public JSONObject getWebsiteVisitInfo()
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Websitevisitcount websitevisitcount=websitevisitcountService.getWebsiteVisitInfo();
			
			if(websitevisitcount!=null)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("data", websitevisitcount);
				jsonObject.put("Msg", "获取网站访问量相关数据成功！");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "获取网站访问量相关数据失败！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取网站访问量相关数据失败！");
		}
		return jsonObject;
	}
	
	/**
	 * 修改网站访问量相关数据
	 * @param itemsManageInfos
	 * @return
	 */
	@PostMapping("updateWebsiteVisitCount")
	@ApiOperation(value="修改网站访问量相关数据",notes="修改网站访问量相关数据",produces="application/json")
	public JSONObject addItemByType(@RequestBody @ApiParam(value = "访问量相关数据") Websitevisitcount websitevisitcount)
	{
		JSONObject jsonObject = new JSONObject();
		try 
		{
			int count=websitevisitcountService.updateVisitCount(websitevisitcount);
			
			if(count>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "修改网站访问量相关数据成功！");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "修改网站访问量相关数据失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "修改网站访问量相关数据失败！");
		}
		return jsonObject;
	}
}
