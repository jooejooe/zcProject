package com.xzx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.model.Docapply;
import com.xzx.model.Document;
import com.xzx.service.IDocapplyService;
import com.xzx.service.IDocumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/document")
@Api(value="文书查询相关接口",description="document")
public class DocumentController {
	@Autowired
	IDocumentService documentService;
	
	@Autowired
	IDocapplyService docapplyService;
	
	@GetMapping("getDocumentListLayui")
	@ApiOperation(value="(个人用户或司法机关)文书查询列表【layui控件专用】",notes="文书查询列表【layui控件专用】",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="searchType",value="搜索类型【0：个人用户；2：司法机关】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="modelType",value="搜索业务类型【0 法律公证 1 司法鉴定 2 人民仲裁 3 法律援助】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="sfzh",value="搜索身份证号【个人用户必须输入，其他搜索类型可为空】",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="name",value="搜索姓名",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="telephone",value="搜索手机号",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="caseNo",value="搜索案卷编号",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="page",value="当前页码",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="limit",value="当前页码",dataType="String",required=true,paramType="query")
	})
	public JSONObject getDocumentListLayui(
			@RequestParam("searchType") String searchType,@RequestParam("modelType") String modelType,String sfzh,String name,String telephone,String caseNo,@RequestParam("page") String page,@RequestParam("limit") String limit)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{	
			List<Document> listDoc=new ArrayList<Document>();
			
			int pageindex=Integer.parseInt(page);//当前页码
			int pageSize=Integer.parseInt(limit);//每页条数
			
			PageHelper.startPage(pageindex,pageSize);
			
			if(searchType.equals("0"))
			{
				listDoc=documentService.getPersonalDocsByParam(sfzh, Integer.parseInt(modelType));
			}
			else if(searchType.equals("1"))
			{
				listDoc=documentService.getPersonalDocsByManual(sfzh, name, telephone, caseNo, Integer.parseInt(modelType));
			}
			else 
			{
				listDoc=documentService.getPersonalDocsByDepartment(sfzh, name, telephone, Integer.parseInt(modelType));
			}
			
			PageInfo<Document> pageList = new PageInfo<Document>(listDoc);
			
			jsonObject.put("data", pageList.getList());
			jsonObject.put("code", "0");
			jsonObject.put("Message", "获取文书查询分页列表成功！");
			jsonObject.put("count", pageList.getTotal());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("code", "1");
			jsonObject.put("Message", "获取文书查询分页列表失败！");
		}
		return jsonObject;
	}
	
	@GetMapping("getDocumentList")
	@ApiOperation(value="(个人用户或司法机关)文书查询列表",notes="文书查询列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="searchType",value="搜索类型【0：个人用户；2：司法机关】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="modelType",value="搜索业务类型【0 法律公证 1 司法鉴定 2 人民仲裁 3 法律援助】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="sfzh",value="搜索身份证号【个人用户必须输入，其他搜索类型可为空】",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="name",value="搜索姓名",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="telephone",value="搜索手机号",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="caseNo",value="搜索案卷编号",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getDocumentList(
			@RequestParam("searchType") String searchType,@RequestParam("modelType") String modelType,String sfzh,String name,String telephone,String caseNo,String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{	
			List<Document> listDoc=new ArrayList<Document>();
			
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				
				PageHelper.startPage(pageindex,pageSize);
				
				if(searchType.equals("0"))
				{
					listDoc=documentService.getPersonalDocsByParam(sfzh, Integer.parseInt(modelType));
				}
				else if(searchType.equals("1"))
				{
					listDoc=documentService.getPersonalDocsByManual(sfzh, name, telephone, caseNo, Integer.parseInt(modelType));
				}
				else 
				{
					listDoc=documentService.getPersonalDocsByDepartment(sfzh, name, telephone, Integer.parseInt(modelType));
				}
				
				PageInfo<Document> page = new PageInfo<Document>(listDoc);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取文书查询分页列表成功！");
			}
			else
			{			
				if(searchType.equals("0"))
				{
					listDoc=documentService.getPersonalDocsByParam(sfzh, Integer.parseInt(modelType));
				}
				else if(searchType.equals("1"))
				{
					listDoc=documentService.getPersonalDocsByManual(sfzh, name, telephone, caseNo, Integer.parseInt(modelType));
				}
				else 
				{
					listDoc=documentService.getPersonalDocsByDepartment(sfzh, name, telephone, Integer.parseInt(modelType));
				}
				
				jsonObject.put("data", listDoc);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取文书查询列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取文书查询列表失败！");
		}
		return jsonObject;
	}
	
	@GetMapping("getDocCount")
	@ApiOperation(value="获取满足条件的文书数量，当返回数量为0时，不可点击获取验证码",notes="获取满足条件的文书数量",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="searchType",value="搜索类型【0：个人用户；1：人工查询；2：司法机关】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="modelType",value="搜索业务类型【0 法律公证 1 司法鉴定 2 人民仲裁 3 法律援助】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="sfzh",value="搜索身份证号【个人用户必须输入，其他搜索类型可为空】",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="name",value="搜索姓名",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="telephone",value="搜索手机号",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="caseNo",value="搜索案卷编号",dataType="String",required=false,paramType="query")
	})
	public JSONObject getDocCount(@RequestParam("searchType") String searchType,@RequestParam("modelType") String modelType,String sfzh,String name,String telephone,String caseNo)
	{
		JSONObject jsonObject = new JSONObject();
		
		int count=0;
		try 
		{
			if(searchType.equals("0"))
			{
				count=documentService.matchPersonalDocCount(sfzh, Integer.parseInt(modelType));
			}
			else if(searchType.equals("1"))
			{
				count=documentService.matchItemCount(sfzh, name, telephone, caseNo, Integer.parseInt(modelType));
			}
			else 
			{
				count=documentService.matchItemCountByDepartSearch(sfzh, name, telephone, Integer.parseInt(modelType));
			}
			
			jsonObject.put("data", count);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取满足条件的文书数量成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取满足条件的文书数量失败！");
		}
		
		return jsonObject;
	}
	
	@GetMapping("getDocumentById")
	@ApiOperation(value="获取相应文书id对应的文书详情",notes="获取相应文书id对应的文书详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="文书id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getDocumentById(
			@RequestParam("id") String id)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Document document=documentService.getDocById(id);
			
			jsonObject.put("data", document);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取文书详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取文书详情失败！");
		}
		return jsonObject;
	}
	
	@PostMapping("addDocApply")
	@ApiOperation(value="【文书查询】人工查询申请提交",notes="【文书查询】人工查询申请提交"
			+ "<br/>createUser:提交人用户名"
			+ "<br/>modelType:业务类别(0 公证办理 1 司法鉴定 2 人民仲裁 3 法律援助 4 律师)"
			+ "<br/>name:姓名"
			+ "<br/>sfzh:身份证号"
			+ "<br/>telephone:手机号码",
			produces="application/json")
	public JSONObject addDocApply(@RequestBody @ApiParam(value = "提交文书查询人工查询申请") Docapply docapply)
	{	
		JSONObject jsonObject=new JSONObject();
		try {		
				
			if(docapplyService.addDocApply(docapply))
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "人工查询申请提交成功！");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "人工查询申请提交失败！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "人工查询申请提交失败！");
		}
		
		return jsonObject;
	}
}
