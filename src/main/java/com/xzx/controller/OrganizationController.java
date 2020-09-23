package com.xzx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.model.Department;
import com.xzx.model.Region;
import com.xzx.service.IDepartmentService;
import com.xzx.service.IEquipmentService;
import com.xzx.service.IFairworkerService;
import com.xzx.service.IRegionService;
import com.xzx.viewModel.FairWorkerInfos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/organ")
@Api(value="机构接口",description="organ")
public class OrganizationController {
	@Autowired
	IEquipmentService equipmentService;

	@Autowired
	IRegionService regionService;
	
	@Autowired
	IDepartmentService departmentService;
	
	@Autowired
	IFairworkerService fairworkerService;
	
	@Value("${regionTopCode}")
	int regionTopCode;

	/**
	 * 获取当前机构所属的机构或区域名称
	 * @param token
	 * @return
	 */
	@GetMapping("getCurOrganName")
	@ApiOperation(value="获取当前机构所属的机构或区域名称",notes="根据设备token值获取当前机构所属的机构或区域名称",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",value="设备唯一令牌",dataType="String",required=true,paramType="query")
	})
	public JSONObject getCurOrganName(@RequestParam("token") String token){
		JSONObject jsonObject = new JSONObject();

		try 
		{	
			String name=equipmentService.getCurOrganName(token);

			jsonObject.put("curOrganName", name==null?"":name);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取当前终端机构名称成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取当前终端机构名称失败！");
		}

		return jsonObject;
	}

	/**
	 * 获取当前终端的顶级机构名称
	 * @param token
	 * @return
	 */
	@GetMapping("getTopOrganName")
	@ApiOperation(value="获取当前终端的顶级机构名称",notes="根据当前终端的token获取当前终端的顶级机构名称",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",value="终端的token或相应区域id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="type",value="参数类型（0-token;1-区域id）",dataType="String",required=true,paramType="query")
	})
	public JSONObject getTopOrganName(@RequestParam("token") String token,@Param("type") String type)
	{
		JSONObject jsonObject=new JSONObject();

		try {
			Region region=regionService.getTopRegionName(token,type);
			
			jsonObject.put("regionInfo", region);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取当前终端的顶级机构信息成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取当前终端的顶级机构信息失败！");
		}

		return jsonObject;
	}

	/**
	 * 根据token值或者区域id获取机构区域列表
	 * @param parameter
	 * @param type
	 * @return
	 */
	@GetMapping("getRegionList")
	@ApiOperation(value="获取当前区域机构列表",notes="获取当前区域机构列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="parameter",value="token或者区域id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="type",value="参数值类型[0:token值；1：区域id值]",dataType="String",required=true,paramType="query",allowableValues="0,1")
	})
	public JSONObject getRegionList(@RequestParam("parameter") String parameter,@Param("type") int type)
	{
		JSONObject jsonObject=new JSONObject();

		try
		{
			Region region=new Region();
			
			if(type==0)
			{
				region=regionService.getRegionByToken(parameter);
			}
			else
			{
				region=regionService.getRegionById(parameter);
			}

			if(region!=null)
			{
				String regionCode=region.getRegionCode();

				if(regionCode.length()==regionTopCode)
				{
					//获取下级
					List<Region> list=regionService.getSubRegionListById(Integer.toString(region.getRegionId()));
					
					jsonObject.put("organizationList", list);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "获取机构列表成功！");
				}
				else
				{
					//默认写死的机构类别等
					List<Map<String, String>> list=new ArrayList<Map<String,String>>();
					//1、公证处；2、律师事务所 3 法律援助机构 4 仲裁委员会 5 法律服务所 6 司法鉴定所 7 司法所
					String[] typeIds={"1","2","3","4","5","6","7"};
					String[] typeNames={"公证处","律师事务所","法律援助","人民仲裁机构","法律服务所","司法鉴定所","司法所"};
					
					for(int i=0;i<7;i++)
					{
						Map<String,String> map=new HashMap<>();
						map.put("regionId", typeIds[i]);
						map.put("regionName", typeNames[i]);
						
						list.add(map);
					}
					
					jsonObject.put("organizationList", list);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "获取机构列表成功！");
				}
			}
			else
			{
				jsonObject.put("organizationList", new ArrayList<>());
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取机构列表成功！");
			}
			
			jsonObject.put("curOrganization", region);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取机构列表失败！");
		}
		
		return jsonObject;
	}
	
	/**
	 * 根据机构id获取相应机构信息详情
	 * @param departId
	 * @return
	 */
	@GetMapping("getDepartById")
	@ApiOperation(value="获取相应机构信息",notes="根据机构id获取相应机构信息详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="departId",value="机构id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getDepartById(@RequestParam("departId") String departId)
	{
		JSONObject jsonObject=new JSONObject();

		try
		{
			Department department=departmentService.getDepartById(departId);
			
			jsonObject.put("data", department);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取机构信息成功！");
		}
		catch(Exception e)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取机构信息失败！");
		}
		
		return jsonObject;
	}
	
	/**
	 * 根据相应机构id或设备所在token获取相应机构下的人员列表
	 * @param departId
	 * @return
	 */
	@GetMapping("getFairWorkerByParams")
	@ApiOperation(value="获取人员列表",notes="根据相应机构id或设备所在token获取相应机构下的人员列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="paramId",value="机构id或当前token值；pc端查全部时传空值",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="type",value="请求参数类型(-1:pc端查全部,0:机构id，1：token)",dataType="String",required=true,paramType="query",allowableValues="-1,0,1"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getFairWorkerByParams(@RequestParam("type") String type,String paramId,String... pageParams)
	{
		JSONObject jsonObject=new JSONObject();
		
		try 
		{
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				
				String searchText=pageParams[2];
				
				PageHelper.startPage(pageindex,pageSize);
				List<FairWorkerInfos> list=fairworkerService.getFairWorkerByDepartId(type,paramId,searchText);
				PageInfo<FairWorkerInfos> page = new PageInfo<FairWorkerInfos>(list);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取人员分页列表成功！");
			}
			else
			{
				List<FairWorkerInfos> list=fairworkerService.getFairWorkerByDepartId(type,paramId,"");
				
				jsonObject.put("data", list);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取人员列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取人员列表失败！");
		}
		
		return jsonObject;
	}
}
