package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.UserLoginToken;
import com.xzx.model.Consultregion;
import com.xzx.model.Consulttype;
import com.xzx.model.Fairworker;
import com.xzx.service.IConsultregionService;
import com.xzx.service.IConsulttypeService;
import com.xzx.service.IFairworkerService;
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
@RequestMapping("/artificialConsultation")
@Api(value="人工咨询相关api",description="artificialConsultation")
public class ArtificialConsultationController {
	@Autowired
    IConsultregionService consultregionService;
	
	@Autowired
    IConsulttypeService consulttypeService;
	
	@Autowired
    IFairworkerService fairworkerService;

	/**
	 * 获取人工咨询区域列表
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getConsultRegionList")
	@ApiOperation(value="获取人工咨询区域列表",notes="根据上级区域id获取区域列表",produces="application/json")
	public JSONObject getConsultRegionList(){
		JSONObject jsonObject = new JSONObject();
		
		try 
		{
			List<Consultregion> list=consultregionService.getConsultregionList();
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取人工咨询列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取人工咨询列表失败！");
		}		

		return jsonObject;
	}
	
	/**
	 * 获取人工咨询分类列表
	 * @param parentId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getConsultTypeList")
	@ApiOperation(value="根据所选区域id获取人工咨询类别分类列表",notes="根据所选区域id获取人工咨询类别分类列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "parentId", value = "区域id(-1表示查全部)", dataType = "String", paramType = "query")
	})
	public JSONObject getConsultTypeList(@RequestParam("parentId") String parentId){
		JSONObject jsonObject = new JSONObject();
		
		try 
		{
			List<Consulttype> list=consulttypeService.getConsulttypeList(Integer.parseInt(parentId));
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取人工咨询类别分类列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取人工咨询类别分类列表失败！");
		}		

		return jsonObject;
	}
	
	/**
	 * 获取人工咨询工作人员列表
	 * @param parentId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getConsultWorkerList")
	@ApiOperation(value="根据所选区域id,所选类别id获取人工咨询工作人员列表",notes="根据所选区域id,所选类别id获取人工咨询工作人员列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "regionId", value = "区域id(-1表示查全部)", dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "typeId", value = "类别id(-1表示查全部)", dataType = "String", paramType = "query")
	})
	public JSONObject getConsultWorkerList(@RequestParam("regionId") String regionId,@RequestParam("typeId") String typeId){
		JSONObject jsonObject = new JSONObject();
		
		try 
		{
			List<Fairworker> list=fairworkerService.getConsultworker(Integer.parseInt(regionId),Integer.parseInt(typeId));
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取人工咨询工作人员列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取人工咨询工作人员列表失败！");
		}		

		return jsonObject;
	}
}
