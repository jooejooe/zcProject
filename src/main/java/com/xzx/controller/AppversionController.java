package com.xzx.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xzx.model.Appversion;
import com.xzx.service.IAppversionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  手机端版本更新接口
 * </p>
 *
 * @author Helen
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/appversion")
@Api(value="app更新相关接口",description="appversion")
public class AppversionController {
	@Autowired
	IAppversionService appversionService;
	
	/**
	 * 根据版本号校验app是否需要更新
	 * @return
	 */
	@GetMapping("checkAppVersion")
	@ApiOperation(value="根据版本号校验app是否需要更新",notes="校验app是否需要更新",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="type",value="设备类型（0-安卓；1-ios）",dataType="String",required=true,paramType="query",allowableValues="0,1"),
		@ApiImplicitParam(name="versionCode",value="版本号",dataType="String",required=true,paramType="query")
	})
	public JSONObject checkAppVersion(
			@RequestParam("type") String type,
			@RequestParam("versionCode") String versionCode)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Appversion appversion=appversionService.getAppInfo(type);
			
			if(appversion!=null&&!appversion.getVersion().equals(versionCode))
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("appContext", appversion.getContext());
				jsonObject.put("appUrl", appversion.getAppUrl());
				jsonObject.put("Msg", "有最新的app，请更新！");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "当前app已经是最新！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "校验app是否需要更新失败！");
		}
		return jsonObject;
	}
}
