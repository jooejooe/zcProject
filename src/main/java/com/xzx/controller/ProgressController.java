package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.annotation.UserLoginToken;
import com.xzx.service.ISpeedService;
import com.xzx.viewModel.ProgressInfos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/progress")
@Api(value="进度查询相关接口",description="progress")
public class ProgressController {
	@Autowired
    ISpeedService speedService;
	
	@UserLoginToken
	@GetMapping("getProgressList")
	@ApiOperation(value="终端机用户进度查询",notes="获取在线审批列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="modelType",value="业务类型（特定业务：0 公证办理 1 人民调解 2 司法鉴定 3 法律援助 4 预约公证办理 5  预约人民调解 6 预约司法鉴定 7 预约法律援助 8 预约律师;大类业务：在线申办：0,1,2,3|预约：4,5,6,7,8）",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="userId",value="当前用户id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getProgressList(
			@RequestParam("modelType") String modelType,
			@RequestParam("userId") String userId,
			String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				PageHelper.startPage(pageindex,pageSize);
				List<ProgressInfos> list=speedService.getProgressByParam(modelType,userId);
				PageInfo<ProgressInfos> page = new PageInfo<ProgressInfos>(list);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取进度查询分页列表成功！");
			}
			else
			{
				List<ProgressInfos> list=speedService.getProgressByParam(modelType,userId);
				
				jsonObject.put("data", list);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取进度查询列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取进度查询列表失败！");
		}
		return jsonObject;
	}
	
	@UserLoginToken
	@GetMapping("getProgressListLive")
	@ApiOperation(value="【现场】终端机用户进度查询",notes="获取在线审批列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="modelType",value="业务类型（特定业务：0 公证办理 1 人民调解 2 司法鉴定 3 法律援助 4 预约公证办理 5  预约人民调解 6 预约司法鉴定 7 预约法律援助 8 预约律师;大类业务：在线申办：0,1,2,3|预约：4,5,6,7,8）",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="userId",value="当前用户id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="IsOnline",value="0-在线；1-现场",dataType="String",required=true,paramType="query",allowableValues="0,1"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getProgressListLive(
			@RequestParam("modelType") String modelType,
			@RequestParam("userId") String userId,
			@RequestParam("IsOnline") String IsOnline,
			String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				PageHelper.startPage(pageindex,pageSize);
				List<ProgressInfos> list=speedService.getProgressByParamLive(modelType,userId,IsOnline);
				PageInfo<ProgressInfos> page = new PageInfo<ProgressInfos>(list);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取进度查询分页列表成功！");
			}
			else
			{
				List<ProgressInfos> list=speedService.getProgressByParamLive(modelType,userId,IsOnline);
				
				jsonObject.put("data", list);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取进度查询列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取进度查询列表失败！");
		}
		return jsonObject;
	}
}
