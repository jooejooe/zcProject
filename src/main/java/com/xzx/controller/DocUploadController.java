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
import com.xzx.model.Docupload;
import com.xzx.service.IDocuploadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/docUpload")
@Api(value="文书打印相关接口",description="docUpload")
public class DocUploadController {
	@Autowired
	IDocuploadService docuploadService;
	
	@GetMapping("getDocUploadList")
	@ApiOperation(value="文书打印列表",notes="文书打印列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="title",value="文书标题",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="token",value="设备唯一令牌【仅终端机需要，pc传空】",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="modelType",value="搜索业务类型【0 公证 1 司法鉴定 2 人民仲裁 3 法律援助 4律师】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getDocumentList(
			@RequestParam("modelType") String modelType,String title,String token,String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{	
			List<Docupload> listDoc=new ArrayList<Docupload>();
			
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				
				PageHelper.startPage(pageindex,pageSize);
				
				listDoc=docuploadService.getDocUploadList(title, token, Integer.parseInt(modelType));
				
				PageInfo<Docupload> page = new PageInfo<Docupload>(listDoc);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取文书打印分页列表成功！");
			}
			else
			{			
				listDoc=docuploadService.getDocUploadList(title, token, Integer.parseInt(modelType));
				
				jsonObject.put("data", listDoc);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取文书打印列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取文书打印列表失败！");
		}
		return jsonObject;
	}
	
	@GetMapping("getDocUploadById")
	@ApiOperation(value="获取相应文书打印id对应的文书打印详情",notes="获取相应文书打印id对应的文书打印详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="文书打印id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getDocUploadById(
			@RequestParam("id") String id)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Docupload docupload=docuploadService.getDocUploadById(id);
			
			jsonObject.put("data", docupload);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取文书打印详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取文书打印详情失败！");
		}
		return jsonObject;
	}
}
