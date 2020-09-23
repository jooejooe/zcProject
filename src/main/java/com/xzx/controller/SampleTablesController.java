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
import com.xzx.model.Sampletables;
import com.xzx.service.ISampletablesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sampleTables")
@Api(value="相关表格相关接口",description="sampleTables")
public class SampleTablesController {
	@Autowired
	ISampletablesService sampletablesService;
	
	@GetMapping("getSampleTablesList")
	@ApiOperation(value="获取相关表格列表",notes="获取相关表格列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="title",value="表格标题",dataType="String",required=false,paramType="query"),
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
			List<Sampletables> listTables=new ArrayList<Sampletables>();

			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数

				PageHelper.startPage(pageindex,pageSize);

				listTables=sampletablesService.getSampleTablesList(Integer.parseInt(modelType), title, token);

				PageInfo<Sampletables> page = new PageInfo<Sampletables>(listTables);

				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取相关表格分页列表成功！");
			}
			else
			{			
				listTables=sampletablesService.getSampleTablesList(Integer.parseInt(modelType), title, token);

				jsonObject.put("data", listTables);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取相关表格列表成功！");
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

	@GetMapping("getSampleTablesById")
	@ApiOperation(value="获取相应表格id对应的表格详情",notes="获取相应表格id对应的表格详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="表格id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getSampleTablesById(
			@RequestParam("id") String id)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Sampletables sampletables=sampletablesService.getSampletablesById(id);

			jsonObject.put("data", sampletables);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取表格详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取表格详情失败！");
		}
		return jsonObject;
	}
}
