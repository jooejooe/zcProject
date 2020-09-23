package com.xzx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.model.Lawprovision;
import com.xzx.service.ILawprovisionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lawProvision")
@Api(value="相关法规相关接口",description="lawProvision")
public class LawProvisionController {
	@Autowired
	ILawprovisionService lawprovisionService;

	@GetMapping("getLawProvisionList")
	@ApiOperation(value="获取相关法规列表",notes="获取相关法规列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="title",value="法规标题",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="token",value="设备唯一令牌【仅终端机需要，pc传空】",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="modelType",value="搜索业务类型【0 公证 1 司法鉴定 2 人民仲裁 3 法律援助 4律师】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getLawProvisionList(
			@RequestParam("modelType") String modelType,String title,String token,String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{	
			List<Lawprovision> listLawProvision=new ArrayList<Lawprovision>();

			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数

				PageHelper.startPage(pageindex,pageSize);

				listLawProvision=lawprovisionService.getLawProvisionList(Integer.parseInt(modelType), title, token);

				PageInfo<Lawprovision> page = new PageInfo<Lawprovision>(listLawProvision);

				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取相关法规分页列表成功！");
			}
			else
			{			
				listLawProvision=lawprovisionService.getLawProvisionList(Integer.parseInt(modelType), title, token);

				jsonObject.put("data", listLawProvision);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取相关法规列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取相关法规列表失败！");
		}
		return jsonObject;
	}

	@GetMapping("getLawProvisionById")
	@ApiOperation(value="获取相应法规id对应的法规详情",notes="获取相应法规id对应的法规详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="法规id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getLawProvisionById(
			@RequestParam("id") String id)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Lawprovision lawprovision=lawprovisionService.getLawprovisionById(id);

			jsonObject.put("data", lawprovision);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取法规详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取法规详情失败！");
		}
		return jsonObject;
	}
}
