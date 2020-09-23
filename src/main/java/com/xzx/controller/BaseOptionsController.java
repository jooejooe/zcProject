package com.xzx.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.annotation.UserLoginToken;
import com.xzx.model.*;
import com.xzx.service.*;
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
import java.util.Map;

//import com.xzx.annotation.OperationLogDetail;
//import com.xzx.enums.OperationType;
//import com.xzx.enums.OperationUnit;

/**
 * <p>
 *  相关基本信息前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/baseOptions")
@Api(value="相关基础数据api",description="baseOptions")
public class BaseOptionsController {
	
	@Autowired
	private IRegionService regionService;

	@Autowired
	private IPurposeService purposeService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IFairworkerService fairworkerService;

	@Autowired
	private IUseplaceService userplaceService;

	@Autowired
	private IBiddingmattersService biddingmattersService;

	@Autowired
	private IAssistanceService assistanceService;

	@Autowired
	private IDictionariesService dictionariesService;

	@Autowired
	private IBiddingmanagersmoneyService biddingmanagersmoneyService;

	@Autowired
	private IAuthenticmaterialService authenticmaterialService;

	@Autowired
    IAppraisalmaterialService appraisalmaterialService;

	@Autowired
    IApplicanttypeService applicanttypeService;

	@Autowired
    ISubscribetypematerialService subscribetypematerialService;

	/**
	 * 获取区域列表
	 * @param request
	 * @return
	 */
	//@OperationLogDetail(detail = "通过上级区域id{{parentId}}获取区域列表",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
	@UserLoginToken
	@GetMapping("getRegionList")
	@ApiOperation(value="获取区域列表",notes="根据上级区域id获取区域列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "parentId", value = "上级id(-1表示查全部)", dataType = "String", paramType = "query")
	})
	public JSONObject getRegionList(@RequestParam("parentId") String parentId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Region> list=regionService.getRegionList(Integer.parseInt(parentId));
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取区域列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取区域列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取公证用途列表
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getPurposeList")
	@ApiOperation(value="获取公证用途列表",notes="获取所有公证用途列表",produces="application/json")
	public JSONObject getPurposeList(){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Purpose> list=purposeService.getPurposeList();
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取公证用途列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取公证用途列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取机构列表
	 * @param departType
	 * @param areaId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getDepartmentList")
	@ApiOperation(value="获取机构列表",notes="根据上级区域id和业务类型获取机构列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="departType",value="业务类型(机构类别(-1、查全部,1、公证处；2、律师事务所 3 法律援助机构 4 调解委员会 5 法律服务所 6 司法鉴定所 7 司法所 8 司法行政机关)",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="areaId",value="-1：查全部；有值：上级区域id",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数,搜索条件】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getDepartmentList(@RequestParam("departType") String departType,@RequestParam("areaId") String areaId,
			String... pageParams){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				String searchText=pageParams[2];

				PageHelper.startPage(pageindex,pageSize);		
				List<Department> list=departmentService.getDepartmentList(departType, areaId,searchText);
				PageInfo<Department> page = new PageInfo<Department>(list);

				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取机构分页列表成功！");
			}
			else
			{
				List<Department> list=departmentService.getDepartmentList(departType, areaId,"");
				jsonObject.put("data", list);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取机构列表成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取机构列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 根据上级区域id和业务类型获取机构（仅返回名称，id）列表
	 * @param departType
	 * @param areaId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getSimpleDepartmentList")
	@ApiOperation(value="获取简版机构列表",notes="根据上级区域id和业务类型获取机构（仅返回名称，id）列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="departType",value="业务类型(机构类别(-1、查全部,1、公证处；2、律师事务所 3 法律援助机构 4 调解委员会 5 法律服务所 6 司法鉴定所 7 司法所 8 司法行政机关)",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="areaId",value="-1：查全部；有值：上级区域id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getSimpleDepartmentList(@RequestParam("departType") String departType,@RequestParam("areaId") String areaId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Department> list=departmentService.getSimpleDepartmentList(departType, areaId);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取机构列表成功！");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取机构列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取工作人员列表
	 * @param RegionId
	 * @param DepartmentId
	 * @param WorkerType
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getWorkerList")
	@ApiOperation(value="获取工作人员列表",notes="根据选择区域id，机构id及业务类型获取工作人员列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="RegionId",value="区域id（-1 查询全部）",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="DepartmentId",value="机构id（-1 查询全部）",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="WorkerType",value="工作人员类别（-1 全部,0 公证员 1 律师 2 人民调解员 3 法律服务人员 4 司法鉴定员 5 司法所人员 6 行政机关人员）",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getWorkerList(@RequestParam("RegionId") String RegionId,@RequestParam("DepartmentId") String DepartmentId,@RequestParam("WorkerType") String WorkerType,String... pageParams){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数

				PageHelper.startPage(pageindex,pageSize);
				List<Fairworker> list=fairworkerService.getWorkerList(RegionId, DepartmentId, WorkerType);
				PageInfo<Fairworker> page = new PageInfo<Fairworker>(list);

				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取工作人员分页列表成功！");
			}
			else 
			{
				List<Fairworker> list=fairworkerService.getWorkerList(RegionId, DepartmentId, WorkerType);
				jsonObject.put("data", list);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取工作人员列表成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取工作人员列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取使用地列表
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getUsePlaceList")
	@ApiOperation(value="获取使用地列表",notes="获取可用的使用地列表",produces="application/json")
	public JSONObject getUsePlaceList(){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Useplace> list=userplaceService.getUsePlaceList();
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取使用地列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取使用地列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取公证办理申办事项列表
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getApplyItemsList")
	@ApiOperation(value="获取申办事项列表",notes="获取可用的申办事项列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId",value="用户id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getApplyItemsList(@RequestParam("userId") String userId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Map<String, Object>> list=biddingmattersService.getBiddingMatterList(Integer.parseInt(userId));
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取申办事项列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取申办事项列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取在线申办类别列表
	 * @param assistancetype
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getAssistanceList")
	@ApiOperation(value="获取在线申办类别列表",notes="根据业务类型获取可用的在线申办类别列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="assistancetype",value="在线服务类别（在线服务各类别字典  0 援助类型 1 调解类别 2 鉴定类别）",dataType="String",required=false,paramType="query",allowableValues="0,1,2"),
		@ApiImplicitParam(name="userId",value="用户id",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="modelType",value="业务类型（1 在线人民调解 2 在线司法鉴定 3 在线法律援助  6预约司法鉴定）",dataType="String",required=false,paramType="query",allowableValues="1,2,3,6"),
		@ApiImplicitParam(name="isSubscribe",value="是否是预约【专为司法鉴定预约业务】：0-预约；1-在线",dataType="String",required=false,paramType="query",allowableValues="0,1")
	})
	public JSONObject getAssistanceList(@RequestParam("assistancetype") String assistancetype,@RequestParam("userId") String userId,@RequestParam("modelType") String modelType,@RequestParam("isSubscribe")String isSubscribe){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Map<String, String>> list=assistanceService.getAssistanceList(assistancetype, userId, modelType,isSubscribe);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取在线申办类别列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取在线申办类别列表失败！");
		}		

		return jsonObject;
	}

	@UserLoginToken
	@GetMapping("getAllAssistanceList")
	@ApiOperation(value="获取所有在线申办类别列表",notes="根据业务类型获取所有在线申办类别列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="assistancetype",value="在线服务类别（在线服务各类别字典  0 援助类型 1 调解类别 2 鉴定类别）",dataType="String",required=false,paramType="query",allowableValues="0,1,2"),
	})
	public JSONObject getAllAssistanceList(@RequestParam("assistancetype") String assistancetype){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Assistance> list=assistanceService.getAllAssistanceList(assistancetype);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取所有在线申办类别列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取所有在线申办类别列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取（性别、学历等）数据字典列表
	 * @param DictionariesTypeId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getDictionaryList")
	@ApiOperation(value="获取（性别、学历等）数据字典列表",notes="获取相应类别的数据字典数据列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="DictionariesTypeId",value="数据类别编码（1 性别  2 学历  3 职业  4 民族  5 证件类型  6 政治面貌  7  专业职称  8 职务  9 融云服务(原法律状态)  10 对方类型  11 预约类型）",dataType="String",required=false,paramType="query")
	})
	public JSONObject getDictionaryList(@RequestParam("DictionariesTypeId") String DictionariesTypeId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Dictionaries> list=dictionariesService.getDictionaryList(DictionariesTypeId);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取数据字典列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取数据字典列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取各类业务所需附件及金额
	 * @param type
	 * @param businessId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getAttachmentsInfo")
	@ApiOperation(value="获取各类业务所需附件及金额",notes="根据业务类型type，细类id获取相关业务所需附件及金额",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="type",value="业务类型(0-公证;1-人民调解;2-司法鉴定;3-法律援助;4-预约)",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="businessId",value="相应业务类型下具体类别id(公证：申办业务id；调解：调解类别id；司法:鉴定类别id；援助：申请人类型id,预约：预约类型id)",dataType="String",required=false,paramType="query")
	})
	public JSONObject getAttachmentsInfo(@RequestParam("type") String type,@RequestParam("businessId") String businessId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			switch (type) {
			case "0"://公证办理
			{
				Biddingmanagersmoney moneyInfo=biddingmanagersmoneyService.getMatterMoneyInfo(businessId);
				if(moneyInfo!=null)
				{
					jsonObject.put("data", moneyInfo);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "获取申办业务及其费用成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "对应申办业务的相关费用及附件信息不存在！");
				}
				break;
			}			
			case "1"://人民调解
				Authenticmaterial authenticmaterial=authenticmaterialService.getAuthenticmaterialInfo(businessId);

				if(authenticmaterial!=null)
				{
					jsonObject.put("data", authenticmaterial);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "获取人民调解相关附件成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "对应人民调解申办业务的相关附件信息不存在！");
				}
				break;
			case "2"://司法鉴定
				Appraisalmaterial appraisalmaterial=appraisalmaterialService.getAppraisalmaterialInfo(businessId);

				if(appraisalmaterial!=null)
				{
					jsonObject.put("data", appraisalmaterial);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "获取司法鉴定相关附件成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "对应司法鉴定的相关附件信息不存在！");
				}

				break;
			case "3"://法律援助
				Applicanttype applicanttype=applicanttypeService.getApplicanttypeInfo(businessId);

				if(applicanttype!=null)
				{
					jsonObject.put("data", applicanttype);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "获取法律援助相关附件成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "对应法律援助的相关附件信息不存在！");
				}

				break;
			case "4"://预约
				Subscribetypematerial subscribetypematerial=subscribetypematerialService.getSubscribetypematerialInfo(businessId);

				if(subscribetypematerial!=null)
				{
					jsonObject.put("data", subscribetypematerial);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "获取预约相关附件成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "对应预约的相关附件信息不存在！");
				}

				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取相关业务所需附件及金额失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取申请人类型列表
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getApplyPersonList")
	@ApiOperation(value="【法律援助】获取申请人类型列表",notes="获取可用的申请人类型列表",produces="application/json")
	public JSONObject getApplyPersonList(){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Applicanttype> list=applicanttypeService.getApplicanttypeList();
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取申请人类型列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取申请人类型列表失败！");
		}		

		return jsonObject;
	}

	/**
	 * 获取工作人员详情
	 * @param fairworkerId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getFairworkerInfo")
	@ApiOperation(value="获取工作人员详情",notes="获取工作人员详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "fairworkerId", value = "工作人员id", dataType = "String", paramType = "query")
	})
	public JSONObject getFairworkerInfo(@RequestParam("fairworkerId") String fairworkerId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Fairworker fairworker=fairworkerService.getFairworkById(Integer.parseInt(fairworkerId));
			jsonObject.put("data", fairworker);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取工作人员详情成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取工作人员详情失败！");
		}		

		return jsonObject;
	}
	
	@UserLoginToken
	@GetMapping("getSecondAssignWorkerList")
	@ApiOperation(value="获取再指派工作人员列表",notes="获取再指派工作人员列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="RegionId",value="区域id（-1 查询全部）",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="DepartmentId",value="机构id（-1 查询全部）",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="WorkerType",value="工作人员类别（-1 全部,0 公证员 1 律师 2 人民调解员 3 法律服务人员 4 司法鉴定员 5 司法所人员 6 行政机关人员）",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="FirstFairworkerId",value="首次指派工作人员id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getSecondAssignWorkerList(@RequestParam("RegionId") String RegionId,@RequestParam("DepartmentId") String DepartmentId,@RequestParam("WorkerType") String WorkerType,@RequestParam("FirstFairworkerId")String FirstFairworkerId){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Fairworker> list=fairworkerService.getSecondAssignWorkerList(RegionId, DepartmentId, WorkerType, FirstFairworkerId);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取再指派工作人员列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取再指派工作人员列表失败！");
		}		

		return jsonObject;
	}
}
