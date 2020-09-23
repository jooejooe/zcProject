package com.xzx.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.xzx.annotation.UserLoginToken;
import com.xzx.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.common.Base64;
//import com.xzx.common.WebIATWS;
import com.xzx.common.WordToPdf;
import com.xzx.model.Access;
import com.xzx.model.Access_bczj;
import com.xzx.model.Access_tjyupload;
import com.xzx.model.Access_wait;
import com.xzx.model.Aj_tjbl;
import com.xzx.model.Aj_tjblls;
import com.xzx.model.Authentic;
import com.xzx.model.Document;
import com.xzx.model.Dzjz;
import com.xzx.model.Fy_sp;
import com.xzx.model.Pq;
import com.xzx.model.Register;
import com.xzx.model.Tjy_access;
import com.xzx.model.Zzlog;
import com.xzx.model.Zzlsb;
import com.xzx.viewModel.AllPqInfo;
import com.xzx.viewModel.AuthenticDetailInfo;
import com.xzx.viewModel.CaseAssignInfo;
import com.xzx.viewModel.CaseAssignInfos;
import com.xzx.viewModel.OtherAccessInfos;
import com.xzx.viewModel.PqInfos;
import com.xzx.viewModel.TJLZInfo;
import com.xzx.viewModel.TJYAccessInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.WebSocket;

@RestController
@RequestMapping("/case")
@Api(value="现场受案仲裁相关接口",description="case")
public class CaseController {
	@Autowired
	IAuthenticService authenticService;

	@Autowired
	IAccessService accessService;

	@Autowired
	IRegisterService registerService;

	@Autowired
	IOtherpartyService otherpartyService;

	@Autowired
	IAccess_bczjService access_bczjService;

	@Autowired
	IFy_spService fy_spService;

	@Autowired
	IDzjzService dzjzService;

	@Autowired
	IDocumentService documentService;

	@Autowired
	IPqService pqService;

	@Autowired
	ITjy_accessService tjy_accessService;

	@Autowired
	IAccess_tjyuploadService access_tjyuploadService;

	@Autowired
	IZzlsbService zzlsbService;

	@Autowired
	IZzlogService zzlogService;

	@Autowired
	IAccess_waitService access_waitService;

	@Autowired
	IAj_tjbllsService aj_tjbllsService;

	@Autowired
	IAj_tjblService aj_tjblService;

	@Autowired
	IFairworkerService fairworkerService;

	@Value("${file.uploadFolder}")
	private String uploadFolder;



	/**
	 * 案件指派列表
	 * @param pageindex
	 * @param pagesize
	 * @param regionId
	 * @param caseType
	 * @param spState
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getAssignCaseListZc")
	@ApiOperation(value="案件指派列表",notes="案件指派列表",produces="application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name="page",value="当前页码",dataType="String",required=true,paramType="query"),
			@ApiImplicitParam(name="limit",value="每页条数",dataType="String",required=true,paramType="query"),
			@ApiImplicitParam(name="regionId",value="【operateType=1】当前登录人所属区域id或【operateType=2】当前登录人id",dataType="String",required=true,paramType="query"),
			@ApiImplicitParam(name="caseType",value="案件类型",dataType="String",required=false,paramType="query"),
			@ApiImplicitParam(name="caseTypeSecond",value="案件类型二级",dataType="String",required=false,paramType="query"),
			@ApiImplicitParam(name="spState",value="审批状态",dataType="String",required=false,paramType="query"),
			@ApiImplicitParam(name="starttime",value="审批开始时间",dataType="String",required=false,paramType="query"),
			@ApiImplicitParam(name="endtime",value="审批结束时间",dataType="String",required=false,paramType="query"),
			@ApiImplicitParam(name="dsrSFZH",value="当事人身份证号",dataType="String",required=false,paramType="query"),
			@ApiImplicitParam(name="operateType",value="操作类型（1-主任端获取案件列表；2-仲裁员端获取案件列表）",dataType="String",required=false,paramType="query",allowableValues="1,2"),
			@ApiImplicitParam(name="isOnline",value="案件来源（-1 全部 0-线上；1-现场）",dataType="String",required=true,paramType="query"),
			@ApiImplicitParam(name="orderType",value="排序类别（ 0-按创建时间倒序；1-按排期开始时间倒序）",dataType="String",required=true,paramType="query")
	})
	public JSONObject getAssignCaseListZc(@RequestParam("page")String pageindex,@RequestParam("limit")String pagesize,@RequestParam("regionId")String regionId,
										String caseType,String caseTypeSecond,String spState,String starttime,String endtime,String dsrSFZH,@RequestParam("operateType")String operateType,@RequestParam("isOnline")String isOnline,@RequestParam("orderType")String orderType)
	{
		JSONObject jsonObject = new JSONObject();

		try
		{
			PageHelper.startPage(Integer.parseInt(pageindex),Integer.parseInt(pagesize));

			List<CaseAssignInfos> list=authenticService.getAssignCaseListZc(regionId,caseType,caseTypeSecond,spState,starttime,endtime,dsrSFZH,operateType,Integer.parseInt(isOnline),orderType);
			if(list != null && list.size()>0){
				for(int i=0; i<list.size(); i++){
					CaseAssignInfos map = list.get(i);
					String workerids = map.getWorkerName()==null?"":map.getWorkerName();
					if(!"".equals(workerids.trim())){
						String names = fairworkerService.getNames(workerids);
						System.out.println("names------>"+names);
						map.setWorkerName(names);
					}
				}
			}

			PageInfo<CaseAssignInfos> page = new PageInfo<CaseAssignInfos>(list);

			jsonObject.put("data", page.getList());
			jsonObject.put("code", "0");
			jsonObject.put("Message", "获取案件指派分页列表成功！");
			jsonObject.put("count", page.getTotal());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			jsonObject.put("code", "1");
			jsonObject.put("Msg", "获取案件指派分页列表失败！");
		}
		return jsonObject;
	}


	/**
	 * 案件指派列表
	 * @param pageindex
	 * @param pagesize
	 * @param regionId
	 * @param caseType
	 * @param spState
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getAssignCaseList")
	@ApiOperation(value="案件指派列表",notes="案件指派列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page",value="当前页码",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="limit",value="每页条数",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="regionId",value="【operateType=1】当前登录人所属区域id或【operateType=2】当前登录人id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="caseType",value="案件类型",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="spState",value="审批状态",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="starttime",value="审批开始时间",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="endtime",value="审批结束时间",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="dsrSFZH",value="当事人身份证号",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="operateType",value="操作类型（1-主任端获取案件列表；2-仲裁员端获取案件列表）",dataType="String",required=false,paramType="query",allowableValues="1,2"),
		@ApiImplicitParam(name="isOnline",value="案件来源（-1 全部 0-线上；1-现场）",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="orderType",value="排序类别（ 0-按创建时间倒序；1-按排期开始时间倒序）",dataType="String",required=true,paramType="query")
	})
	public JSONObject getAssignCaseList(@RequestParam("page")String pageindex,@RequestParam("limit")String pagesize,@RequestParam("regionId")String regionId,
			String caseType,String spState,String starttime,String endtime,String dsrSFZH,@RequestParam("operateType")String operateType,@RequestParam("isOnline")String isOnline,@RequestParam("orderType")String orderType)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			PageHelper.startPage(Integer.parseInt(pageindex),Integer.parseInt(pagesize));

			List<CaseAssignInfos> list=authenticService.getAssignCaseList(regionId,caseType,spState,starttime,endtime,dsrSFZH,operateType,Integer.parseInt(isOnline),orderType);

			PageInfo<CaseAssignInfos> page = new PageInfo<CaseAssignInfos>(list);

			jsonObject.put("data", page.getList());
			jsonObject.put("code", "0");
			jsonObject.put("Message", "获取案件指派分页列表成功！");
			jsonObject.put("count", page.getTotal());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("code", "1");
			jsonObject.put("Msg", "获取案件指派分页列表失败！");
		}
		return jsonObject;
	}

	/**
	 * 获取案件详情
	 * @param caseId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getCaseInfo")
	@ApiOperation(value="获取案件详情",notes="获取案件详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="docId",value="生成仲裁书id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getCaseInfo(@RequestParam("caseId")String caseId,String... docId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			AuthenticDetailInfo authenticDetailInfo=authenticService.getAuthenticById(caseId);

			String WorkerName = authenticDetailInfo.getWorkerName()==null?"":authenticDetailInfo.getWorkerName();
			if(!"".equals(WorkerName)){
				WorkerName = fairworkerService.getNames(WorkerName);
			}
			authenticDetailInfo.setWorkerName(WorkerName);
			String modelId=authenticDetailInfo.getAuthenticId();
			String personId_A=authenticDetailInfo.getUserId();//申请方当事人id
			String personId_B=authenticDetailInfo.getOtherPartyId();//对方（被申请方）当事人id

			Register personA=registerService.getRegisterInfoById(personId_A);
			Register personB=registerService.getRegisterInfoById(personId_B);

			List<Access> listAccess=accessService.getPartAccessByItemId(modelId, "1");//高拍仪上传的案件附件
			List<Access_bczj> listAccessBCZJ=access_bczjService.getAccessByItemId(modelId, "1");//调查取证相关附件

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String currentTime=df.format(new Date());

			//	        Fy_sp fy_sp=fy_spService.getSPInfoByCaseId(caseId);

			if(docId!=null&&docId.length>0)
			{
				jsonObject.put("docInfos", documentService.getDocById(docId[0]));
			}
			else
			{
				jsonObject.put("docInfos", new Document());
			}

			Dzjz caseDJS=dzjzService.getJZDocList("1", caseId, "0");
			Dzjz caseQRS=dzjzService.getJZDocList("1", caseId, "1");
			Dzjz caseJZ=dzjzService.getJZDocList("1", caseId, "2");

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取案件详情成功！");
			jsonObject.put("caseInfo", authenticDetailInfo);
			jsonObject.put("caseAccess", listAccess);
			jsonObject.put("personA", personA);
			jsonObject.put("personB", personB);
			jsonObject.put("caseAccessOther", listAccessBCZJ);
			jsonObject.put("currentTime", currentTime);
			jsonObject.put("caseDJSUrl", caseDJS==null?"":caseDJS.getAccessurl());
			jsonObject.put("caseQRSUrl", caseQRS==null?"":caseQRS.getAccessurl());
			jsonObject.put("caseJZUrl", caseJZ==null?"":caseJZ.getAccessurl());
			//			jsonObject.put("spInfo", fy_sp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取案件详情失败！");
		}

		return jsonObject;
	}

	/**
	 * 查看案件排期
	 * @param caseId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getCasePqInfo")
	@ApiOperation(value="查看案件排期",notes="查看案件排期",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getCasePqInfo(@RequestParam("caseId")String caseId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			AuthenticDetailInfo authenticDetailInfo=authenticService.getAuthenticById(caseId);

			String modelId=authenticDetailInfo.getAuthenticId();
			String personId_A=authenticDetailInfo.getUserId();//申请方当事人id
			String personId_B=authenticDetailInfo.getOtherPartyId();//对方（被申请方）当事人id

			Register personA=registerService.getRegisterInfoById(personId_A);//registerService.selectById(personId_A);//申请方当事人信息
			Register personB=registerService.getRegisterInfoById(personId_B);//registerService.selectById(personId_B);//对方（被申请方）当事人信息

			List<Access> listAccess=accessService.getPartAccessByItemId(modelId, "1");//高拍仪上传的案件附件

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "查看案件排期成功！");
			jsonObject.put("caseInfo", authenticDetailInfo);
			jsonObject.put("caseAccess", listAccess);
			jsonObject.put("personA", personA);
			jsonObject.put("personB", personB);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "查看案件排期失败！");
		}

		return jsonObject;
	}

	/**
	 * 查看案件电子卷宗
	 * @param caseId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getCaseDzjzInfo")
	@ApiOperation(value="查看案件电子卷宗",notes="查看案件电子卷宗",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getCaseDzjzInfo(@RequestParam("caseId")String caseId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			AuthenticDetailInfo authenticDetailInfo=authenticService.getAuthenticById(caseId);

			String modelId=authenticDetailInfo.getAuthenticId();
			String personId_A=authenticDetailInfo.getUserId();//申请方当事人id
			String personId_B=authenticDetailInfo.getOtherPartyId();//对方（被申请方）当事人id

			Register personA=registerService.getRegisterInfoById(personId_A);
			Register personB=registerService.getRegisterInfoById(personId_B);

			List<Access> listAccess=accessService.getPartAccessByItemId(modelId, "1");//高拍仪上传的案件附件

			Dzjz caseDJS=dzjzService.getJZDocList("1", caseId, "0");
			Dzjz caseQRS=dzjzService.getJZDocList("1", caseId, "1");
			Dzjz caseJZ=dzjzService.getJZDocList("1", caseId, "2");

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "查看案件电子卷宗成功！");
			jsonObject.put("caseInfo", authenticDetailInfo);
			jsonObject.put("caseAccess", listAccess);
			jsonObject.put("personA", personA);
			jsonObject.put("personB", personB);
			jsonObject.put("caseDJSUrl", caseDJS==null?"":caseDJS.getAccessurl());
			jsonObject.put("caseQRSUrl", caseQRS==null?"":caseQRS.getAccessurl());
			jsonObject.put("caseJZUrl", caseJZ==null?"":caseJZ.getAccessurl());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "查看案件电子卷宗失败！");
		}

		return jsonObject;
	}

	/**
	 * 查看证据上传
	 * @param caseId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getCaseZjscInfo")
	@ApiOperation(value="查看证据上传",notes="查看证据上传",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getCaseZjscInfo(@RequestParam("caseId")String caseId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			AuthenticDetailInfo authenticDetailInfo=authenticService.getAuthenticById(caseId);

			String modelId=authenticDetailInfo.getAuthenticId();
			String personId_A=authenticDetailInfo.getUserId();//申请方当事人id
			String personId_B=authenticDetailInfo.getOtherPartyId();//对方（被申请方）当事人id

			Register personA=registerService.getRegisterInfoById(personId_A);
			Register personB=registerService.getRegisterInfoById(personId_B);

			List<Access> listAccess=accessService.getPartAccessByItemId(modelId, "1");//高拍仪上传的案件附件
			List<Access_bczj> listAccessBCZJ=access_bczjService.getAccessByItemId(modelId, "1");//调查取证相关附件

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "查看证据上传成功！");
			jsonObject.put("caseInfo", authenticDetailInfo);
			jsonObject.put("caseAccess", listAccess);
			jsonObject.put("caseAccessOther", listAccessBCZJ);
			jsonObject.put("personA", personA);
			jsonObject.put("personB", personB);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "查看证据上传失败！");
		}

		return jsonObject;
	}

	/**
	 * 再指派详情
	 * @param caseId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getCaseAgainAssignInfo")
	@ApiOperation(value="再指派详情",notes="再指派详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getCaseAgainAssignInfo(@RequestParam("caseId")String caseId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			AuthenticDetailInfo authenticDetailInfo=authenticService.getAuthenticById(caseId);

			String modelId=authenticDetailInfo.getAuthenticId();
			String personId_A=authenticDetailInfo.getUserId();//申请方当事人id
			String personId_B=authenticDetailInfo.getOtherPartyId();//对方（被申请方）当事人id

			Register personA=registerService.getRegisterInfoById(personId_A);
			Register personB=registerService.getRegisterInfoById(personId_B);

			List<Access> listAccess=accessService.getPartAccessByItemId(modelId, "1");//高拍仪上传的案件附件

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "再指派详情成功！");
			jsonObject.put("caseInfo", authenticDetailInfo);
			jsonObject.put("caseAccess", listAccess);
			jsonObject.put("personA", personA);
			jsonObject.put("personB", personB);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "再指派详情失败！");
		}

		return jsonObject;
	}

	/**
	 * 获取相应附件详情
	 * @param accessId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getAccessInfo")
	@ApiOperation(value="获取相应附件详情",notes="获取相应附件详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accessId",value="附件id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getAccessInfo(@RequestParam("accessId")String accessId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Access access=accessService.selectById(accessId);

			jsonObject.put("data", access);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取附件详情成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取附件详情失败！");
		}
		return jsonObject;
	}

	/**
	 * 案件指派
	 * @param caseAssignInfo
	 * @return
	 */
	@UserLoginToken
	@PostMapping("caseAssign")
	@ApiOperation(value="案件指派",notes="案件指派",produces="application/json")
	public JSONObject caseAssign(@RequestBody @ApiParam(value = "指派信息")CaseAssignInfo caseAssignInfo)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(authenticService.caseAssign(caseAssignInfo))
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "案件指派成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "案件指派失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "案件指派失败！");
		}

		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("casePq")
	@ApiOperation(value="案件排期",notes="案件排期",produces="application/json")
	public JSONObject casePq(@RequestBody @ApiParam(value = "排期信息")Pq pq)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			int flag=authenticService.casePq(pq);

			if(flag==-2)
			{
				jsonObject.put("Code", "33333333");
				jsonObject.put("Msg", "所选排期范围已有其他案件排期，请重新选择！");
			}
			else if(flag==-1)
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "排期时间已过，无法重新排期！");
			}
			else if(flag==1)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "案件排期成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "案件排期失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "案件排期失败！");
		}

		return jsonObject;
	}

	@UserLoginToken
	@GetMapping("getIsCanPq")
	@ApiOperation(value="获取是否可以排期",notes="获取是否可以排期",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="pqStart",value="排期开始时间",dataType="String",required=true,paramType="query")
	})
	public JSONObject getIsCanPq(@RequestParam("pqStart")String pqStart)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(pqStart==null||pqStart.equals(""))
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "可以操作排期！");
			}
			else
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date pqStartDate=sdf.parse(pqStart);
				Date dateNow=new Date();

				if (pqStartDate.getTime() <= dateNow.getTime()) 
				{
					jsonObject.put("Code", "22222222");
					jsonObject.put("Msg", "排期时间已过，无法重新排期！");
				}
				else 
				{
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "可以操作排期！");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取是否可操作排期失败！");
		}

		return jsonObject;
	}

	/**
	 * 文件上传
	 * @param file 上传文件
	 * @param controlId（与前端配合，前端控件id）
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@UserLoginToken
	@PostMapping("fileUpload")
	public JSONObject fileUpload(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "controlId")String controlId,HttpServletRequest request) throws IllegalStateException, IOException 
	{
		JSONObject jsonObject = new JSONObject();

		String FileType = request.getParameter("FileType");//文件夹名称

		String filename = UUID.randomUUID().toString().replaceAll("-", "");

		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);//FilenameUtils.getExtension(file.getOriginalFilename());
		String filenames = filename + "." + ext;

		File dir = new File(uploadFolder);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		File dirType=new File(uploadFolder+"/"+FileType);
		if (!dirType.isDirectory()) {
			dirType.mkdirs();
		}

		String pathname = uploadFolder+"/"+FileType+"/"+filenames;
		File uploadFile = new File(pathname);
		FileCopyUtils.copy(file.getBytes(), uploadFile);

		//为doc时同时进行转存pdf 先上传，再转换
		if (ext.equals("doc")||ext.equals("docx")){
			filenames =filename+".pdf";
			WordToPdf d = new WordToPdf();
			String worfile = uploadFolder+"/"+FileType+"/"+filename+"."+ext;
			String pdffile = uploadFolder+"/"+FileType+"/"+filename+".pdf";
			d.wordToPDF(worfile, pdffile);         
		}

		jsonObject.put("filename", file.getOriginalFilename());
		jsonObject.put("msg", "上传成功");
		jsonObject.put("Code", "00000000");
		jsonObject.put("fileUrl", "/"+FileType+"/"+filenames);
		jsonObject.put("uploadControlId", controlId);
		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("addCaseZJ")
	@ApiOperation(value="案件调查取证",notes="案件调查取证",produces="application/json")
	public JSONObject addCaseZJ(@RequestBody @ApiParam(value = "调查取证信息")OtherAccessInfos otherAccessInfos)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			int flag=access_bczjService.insertBatchAccessZJ(otherAccessInfos.getListZJ(),otherAccessInfos.getModelId(),otherAccessInfos.getModelType());

			if(flag>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "添加调查取证成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "添加调查取证失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "添加调查取证失败！");
		}

		return jsonObject;
	}

	/**
	 * 案件审批
	 * @param fy_sp
	 * @return
	 */
	@UserLoginToken
	@PostMapping("caseSP")
	@ApiOperation(value="案件审批",notes="案件审批",produces="application/json")
	public JSONObject caseSP(@RequestBody @ApiParam(value = "审批信息")Fy_sp fy_sp)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(fy_spService.operateSP(fy_sp))
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "案件审批成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "案件审批失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "案件审批失败！");
		}

		return jsonObject;
	}

	/**
	 * 录入卷宗
	 * @param dzjz
	 * @return
	 */
	@UserLoginToken
	@PostMapping("addCaseJZ")
	@ApiOperation(value="录入卷宗",notes="录入卷宗",produces="application/json")
	public JSONObject addCaseJZ(@RequestBody @ApiParam(value = "卷宗信息")Dzjz dzjz)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(dzjzService.getCaseNoCount(dzjz.getBh(),Integer.toString(dzjz.getJzId()))==0)
			{
				if(dzjz.getJzId()==-1)
				{
					if(dzjzService.addJZ(dzjz)>0)
					{
						jsonObject.put("Code", "00000000");
						jsonObject.put("Msg", "录入卷宗成功！");
					}
					else 
					{
						jsonObject.put("Code", "11111111");
						jsonObject.put("Msg", "录入卷宗失败！");
					}
				}
				else
				{
					if(dzjzService.updateJZ(dzjz)>0)
					{
						jsonObject.put("Code", "00000000");
						jsonObject.put("Msg", "录入卷宗成功！");
					}
					else 
					{
						jsonObject.put("Code", "11111111");
						jsonObject.put("Msg", "录入卷宗失败！");
					}
				}
			}
			else
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "卷宗编号已存在！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "录入卷宗失败！");
		}

		return jsonObject;
	}

	/**
	 * 录入仲裁文书
	 * @param document
	 * @return
	 */
	@UserLoginToken
	@PostMapping("addCaseDoc")
	@ApiOperation(value="录入仲裁文书",notes="录入仲裁文书",produces="application/json")
	public JSONObject addCaseDoc(@RequestBody @ApiParam(value = "仲裁文书信息")Document document)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(documentService.getCaseNoCount(document.getFileNumber(),Integer.toString(document.getDocId()))==0)
			{
				if(document.getDocId()!=-1)
				{
					if(documentService.updateDoc(document)>0)
					{
						jsonObject.put("Code", "00000000");
						jsonObject.put("Msg", "录入仲裁文书成功！");
					}
					else 
					{
						jsonObject.put("Code", "11111111");
						jsonObject.put("Msg", "录入仲裁文书失败！");
					}
				}
				else
				{
					if(documentService.addDoc(document)>0)
					{
						jsonObject.put("Code", "00000000");
						jsonObject.put("Msg", "录入仲裁文书成功！");
					}
					else 
					{
						jsonObject.put("Code", "11111111");
						jsonObject.put("Msg", "录入仲裁文书失败！");
					}
				}
			}
			else
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "案卷号已存在！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "录入仲裁文书失败！");
		}

		return jsonObject;
	}

	/**
	 * 获取相应用户的排期列表
	 * @param pageindex
	 * @param pagesize
	 * @param userId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getPqListByUserId")
	@ApiOperation(value="获取相应用户的排期列表",notes="获取相应用户的排期列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page",value="当前页码",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="limit",value="每页条数",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="userId",value="用户id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getPqListByUserId(@RequestParam("page")String pageindex,@RequestParam("limit")String pagesize,@RequestParam("userId")String userId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			PageHelper.startPage(Integer.parseInt(pageindex),Integer.parseInt(pagesize));

			List<PqInfos> list=pqService.getPqListByUser(userId);

			PageInfo<PqInfos> page = new PageInfo<PqInfos>(list);

			jsonObject.put("data", page);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取相应用户的排期列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取相应用户的排期列表失败！");
		}
		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("caseTJBL")
	@ApiOperation(value="修改案件仲裁笔录",notes="修改案件仲裁笔录",produces="application/json")
	public JSONObject caseTJBL(@RequestBody @ApiParam(value = "案件仲裁笔录相关信息")Authentic authentic)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			int flag=authenticService.tjblAuthentic(authentic.getTjbl(), Integer.toString(authentic.getAuthenticId()));

			if(flag>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "修改案件仲裁笔录成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "修改案件仲裁笔录失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "修改案件仲裁笔录失败！");
		}

		return jsonObject;
	}

	/**
	 * 案件仲裁流转
	 * @param tjlzInfo
	 * @return
	 */
	@UserLoginToken
	@PostMapping("caseTJLZ")
	@ApiOperation(value="案件仲裁流转",notes="案件仲裁流转",produces="application/json")
	public JSONObject caseTJLZ(@RequestBody @ApiParam(value = "仲裁流转信息")TJLZInfo tjlzInfo)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(authenticService.tjlzAuthentic(tjlzInfo))
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "案件仲裁流转成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "案件仲裁流转失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "案件仲裁流转失败！");
		}

		return jsonObject;
	}

	/**
	 * 获取相应工作人员的排期列表
	 * @param fairworkerId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getAllPqListByWorkerId")
	@ApiOperation(value="获取相应工作人员的排期列表",notes="获取相应工作人员的排期列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="fairworkerId",value="工作人员id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="caseId",value="案件id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getAllPqListByWorkerId(@RequestParam("fairworkerId")String fairworkerId,@RequestParam("caseId")String caseId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<PqInfos> list=pqService.getAllPqListByUser(fairworkerId,caseId);

			jsonObject.put("data", list);
			jsonObject.put("code", "0");
			jsonObject.put("Message", "获取相应工作人员的排期列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("code", "1");
			jsonObject.put("Msg", "获取相应工作人员的排期列表失败！");
		}
		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("caseAssignAgain")
	@ApiOperation(value="案件再指派",notes="案件再指派",produces="application/json")
	public JSONObject caseAssignAgain(@RequestBody @ApiParam(value = "再指派信息")CaseAssignInfo caseAssignInfo)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(authenticService.caseAssginAgain(Integer.parseInt(caseAssignInfo.getWorkerId()), Integer.parseInt(caseAssignInfo.getModelId()), Integer.parseInt(caseAssignInfo.getZprId()))==0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "案件再指派成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "案件再指派失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "案件再指派失败！");
		}

		return jsonObject;
	}

	/**
	 * 语音转文字测试
	 * @return
	 */
	//	@GetMapping("getVoiceToWord")
	//	@ApiOperation(value="获取语音转文字",notes="获取语音转文字",produces="application/json")
	//	public JSONObject getVoiceToWord()
	//	{
	//		JSONObject jsonObject = new JSONObject();
	//
	//		try 
	//		{
	//			WebIATWS wsIatws=new WebIATWS();
	//			// 构建鉴权url
	//	        String authUrl = wsIatws.getAuthUrl("https://iat-api.xfyun.cn/v2/iat", "72b8f1e27df096604badd994a66d60aa", "5aafed65fc12d4a7453bfebcc7ebd9ff");
	//	        OkHttpClient client = new OkHttpClient.Builder().build();
	//	        String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
	//	        Request request = new Request.Builder().url(url).build();
	//	        WebSocket webSocket = client.newWebSocket(request, new WebIATWS());
	//
	//			jsonObject.put("Code", "00000000");
	//			jsonObject.put("Msg", "获取语音转文字成功！");
	//		} 
	//		catch (Exception e) 
	//		{
	//			e.printStackTrace();
	//			jsonObject.put("Code", "11111111");
	//			jsonObject.put("Msg", "获取语音转文字失败！");
	//		}
	//		return jsonObject;
	//	}

	/**
	 * 获取证据库备选列表
	 * @param caseId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getAuthenticCaseInfo")
	@ApiOperation(value="获取证据库备选列表",notes="获取证据库备选列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="workerId",value="工作人员id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getAuthenticCaseInfo(@RequestParam("caseId")String caseId,@RequestParam("workerId")String workerId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Authentic authentic=authenticService.getCaseInfo(caseId);

			int personId_A=authentic.getUserId();//申请方当事人id
			int personId_B=authentic.getOtherPartyId();//对方（被申请方）当事人id

			Register personA=registerService.getRegisterById(Integer.toString(personId_A));
			Register personB=registerService.getRegisterById(Integer.toString(personId_B));

			List<Access> listAccessA=accessService.getAccessListByItemId(caseId, "1", Integer.toString(personId_A));//申请方附件

			List<Access> listAccessB=accessService.getAccessListByItemId(caseId, "1", Integer.toString(personId_B));//被申请方附件

			List<Access_tjyupload> listAccessUpload=access_tjyuploadService.getUploadAccess(caseId, workerId);

			jsonObject.put("personA_Name", personA.getRealName());
			jsonObject.put("personB_Name", personB.getRealName());
			jsonObject.put("personA_Id", personId_A);
			jsonObject.put("personB_Id", personId_B);
			jsonObject.put("listAccessA", listAccessA);
			jsonObject.put("listAccessB", listAccessB);
			jsonObject.put("uploadAccess", (listAccessUpload==null||listAccessUpload.size()==0)?new ArrayList<Access_tjyupload>():listAccessUpload);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取证据备选列表详情成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取案件详情失败！");
		}

		return jsonObject;
	}

	/**
	 * 操作证据库
	 * @param tjyAccessInfo
	 * @return
	 */
	@UserLoginToken
	@PostMapping("operateAccess")
	@ApiOperation(value="操作证据库",notes="操作证据库",produces="application/json")
	public JSONObject operateAccess(@RequestBody @ApiParam(value = "操作证据库信息")TJYAccessInfo tjyAccessInfo)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{			
			List<Tjy_access> list=tjyAccessInfo.getList();

			for(int num=0;num<list.size();num++)
			{
				Tjy_access tjy_access=list.get(num);

				int accessId=tjy_access.getAccessid();
				Access access=accessService.selectById(accessId);
				String base64Data=access.getAccessInfo();

				Base64 base64 = new Base64();
				String path = base64.base64(base64Data,"tjyAccess",uploadFolder);

				tjy_access.setAccessurl(path);
			}

			List<Map<String, String>> files=tjyAccessInfo.getFileList();

			List<Access_tjyupload> listFile=new ArrayList<Access_tjyupload>();

			if(files!=null&&files.size()>0)
			{
				for(int i=0;i<files.size();i++)
				{	
					Access_tjyupload access_tjyupload=new Access_tjyupload();
					access_tjyupload.setAccessname(files.get(i).get("filename"));
					access_tjyupload.setAccessurl(files.get(i).get("fileUrl"));
					access_tjyupload.setAnjianId(Integer.parseInt(tjyAccessInfo.getCaseId()));
					access_tjyupload.setFairworkerId(Integer.parseInt(tjyAccessInfo.getFairworkerId()));

					listFile.add(access_tjyupload);
				}

				tjyAccessInfo.setListUploadAccess(listFile);
			}

			tjyAccessInfo.setList(list);

			Boolean flag=tjy_accessService.insertTJYAccess(tjyAccessInfo);

			if(flag)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "操作证据库成功！");
			}
			else
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "操作证据库失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "操作证据库失败！");
		}

		return jsonObject;
	}

	/**
	 * 获取当事人列表
	 * @param caseId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getPartyInfo")
	@ApiOperation(value="获取当事人列表",notes="获取当事人列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getPartyInfo(@RequestParam("caseId")String caseId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Authentic authentic=authenticService.getCaseInfo(caseId);

			int personId_A=authentic.getUserId();//申请方当事人id
			int personId_B=authentic.getOtherPartyId();//对方（被申请方）当事人id

			String registerIds=Integer.toString(personId_A)+","+Integer.toString(personId_B);

			List<Register> list=registerService.getRegisterListById(registerIds);

			for(int i=0;i<list.size();i++)
			{
				Register register=list.get(i);

				if(personId_A==register.getUserId())
					register.setUserType("0");

				else if(personId_B==register.getUserId())
					register.setUserType("1");
			}

			jsonObject.put("list", list);

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取当事人列表成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取当事人列表失败！");
		}

		return jsonObject;
	}

	/**
	 * 获取相应仲裁员相应案件的已选择证据列表
	 * @param caseId
	 * @param workerId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getTJYAccessList")
	@ApiOperation(value="获取证据列表",notes="获取相应仲裁员相应案件的已选择证据列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="workerId",value="工作人员id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getTJYAccessList(@RequestParam("caseId")String caseId,@Param("workerId")String workerId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Tjy_access> list=tjy_accessService.getTjyAccessList(caseId, workerId);

			List<Map<String, Object>> listConfirm=zzlogService.getZZConfirmList();

			for(int i=0;i<list.size();i++)
			{
				Tjy_access tjy_access=list.get(i);
				String accessId=Integer.toString(tjy_access.getTjy_accessId());

				List<String> confirmItems=new ArrayList<>();

				for(int j=0;j<listConfirm.size();j++)
				{
					String confirmAccessId=listConfirm.get(j).get("tjy_accessId").toString();
					if(confirmAccessId.equals(accessId))
						confirmItems.add(listConfirm.get(j).get("confirmCount").toString());
				}

				tjy_access.setConfirmList(confirmItems);
			}

			jsonObject.put("list", list);

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取证据列表成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取证据列表失败！");
		}

		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("addZzlsb")
	@ApiOperation(value="添加临时质证记录",notes="添加临时质证记录",produces="application/json")
	public JSONObject addZzlsb(@RequestBody @ApiParam(value = "质证记录")Zzlsb zzlsb)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(zzlsbService.addZzlsb(zzlsb)>0)
			{
				Map<String, String> map=zzlsbService.getZzlsbInfo(zzlsb.getAnjianId(), zzlsb.getFairworkerid(), zzlsb.getTjyaccessId());

				jsonObject.put("confirmCount", map.get("count"));
				jsonObject.put("confirmIds", map.get("ids"));
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "添加临时质证记录成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "添加临时质证记录失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "添加临时质证记录失败！");
		}

		return jsonObject;
	}
	@UserLoginToken
	@PostMapping("addZzlog")
	@ApiOperation(value="添加质证记录",notes="添加质证记录",produces="application/json")
	public JSONObject addZzlog(@RequestBody @ApiParam(value = "质证记录")Zzlog zzlog)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Zzlsb> list=zzlsbService.getLsbListByIds(zzlog.getReason());
			
			String zzlsbIds=zzlog.getReason();
			if(zzlogService.addZzlog(zzlsbIds))
			{		
				jsonObject.put("data", list);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "添加质证记录成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "添加质证记录失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "添加质证记录失败！");
		}

		return jsonObject;
	}

	/**
	 * 获取所有用户的排期列表
	 * @param pageindex
	 * @param pagesize
	 * @param departId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getAllPqList")
	@ApiOperation(value="获取所有用户的排期列表",notes="获取所有用户的排期列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page",value="当前页码",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="limit",value="每页条数",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="departId",value="机构id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getAllPqList(@RequestParam("page")String pageindex,@RequestParam("limit")String pagesize,@RequestParam("departId")String departId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			PageHelper.startPage(Integer.parseInt(pageindex),Integer.parseInt(pagesize));

			List<AllPqInfo> list=authenticService.getAllPqList(departId);

			PageInfo<AllPqInfo> page = new PageInfo<AllPqInfo>(list);

			jsonObject.put("data", page);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取所有用户的排期列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取所有用户的排期列表失败！");
		}
		return jsonObject;
	}

	@UserLoginToken
	@GetMapping("getDSRAccessList")
	@ApiOperation(value="获取当事人证据列表",notes="获取当事人证据列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="caseId",value="案件事项id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="userId",value="当事人id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getDSRAccessList(@RequestParam("caseId")String caseId,@Param("userId")String userId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Tjy_access> list=tjy_accessService.getDsrTjyAccessList(caseId, userId);

			List<Map<String, Object>> listConfirm=zzlogService.getZZConfirmList();

			for(int i=0;i<list.size();i++)
			{
				Tjy_access tjy_access=list.get(i);
				String accessId=Integer.toString(tjy_access.getTjy_accessId());

				List<String> confirmItems=new ArrayList<>();

				for(int j=0;j<listConfirm.size();j++)
				{
					String confirmAccessId=listConfirm.get(j).get("tjy_accessId").toString();
					if(confirmAccessId.equals(accessId))
						confirmItems.add(listConfirm.get(j).get("confirmCount").toString());
				}

				tjy_access.setConfirmList(confirmItems);
			}

			jsonObject.put("list", list);

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取当事人证据列表成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取当事人证据列表失败！");
		}

		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("/deleteFile")
	@ApiOperation(value="删除文件",notes="删除文件",produces="application/json")
	public JSONObject delFile(@RequestBody @ApiParam(value = "要删除的相对路径")String path) {
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String delPath =uploadFolder + path;

			File file = new File(delPath);
			if (file.exists()) {//文件是否存在
				if (file.delete()) {
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "删除文件成功！");
				} else {
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "删除文件失败！");
				}
			} else {
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "文件不存在！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "删除文件失败！");
		}

		return jsonObject;
	}

	/**
	 * 百姓上传等候区证据
	 * @param file
	 * @param FileType
	 * @param caseId
	 * @param userId
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@UserLoginToken
	@PostMapping("waitfileUpload")
	@ApiOperation(value="百姓上传等候区证据",notes="百姓上传等候区证据",produces="application/json")
	public JSONObject waitfileUpload(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "FileType")String FileType,@RequestParam(value = "caseId")String caseId,@RequestParam(value = "userId")String userId,@RequestParam(value="userType")String userType) throws IllegalStateException, IOException 
	{
		JSONObject jsonObject = new JSONObject();

		String filename = UUID.randomUUID().toString().replaceAll("-", "");

		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		String filenames = filename + "." + ext;

		File dir = new File(uploadFolder);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		File dirType=new File(uploadFolder+"/"+FileType);
		if (!dirType.isDirectory()) {
			dirType.mkdirs();
		}

		String pathname = uploadFolder+"/"+FileType+"/"+filenames;
		File uploadFile = new File(pathname);
		FileCopyUtils.copy(file.getBytes(), uploadFile);

		//为doc时同时进行转存pdf 先上传，再转换
		if (ext.equals("doc")||ext.equals("docx")){
			filenames =filename+".pdf";
			WordToPdf d = new WordToPdf();
			String worfile = uploadFolder+"/"+FileType+"/"+filename+"."+ext;
			String pdffile = uploadFolder+"/"+FileType+"/"+filename+".pdf";
			d.wordToPDF(worfile, pdffile);         
		}

		Access_wait access_wait=new Access_wait();
		access_wait.setAccessurl("/"+FileType+"/"+filenames);
		access_wait.setUserId(Integer.parseInt(userId));
		access_wait.setAnjianId(Integer.parseInt(caseId));
		access_wait.setAccessname(file.getOriginalFilename());
		access_wait.setUsertype(userType);

		if(access_waitService.addAccessWait(access_wait)>0)
		{
			jsonObject.put("filename", file.getOriginalFilename());
			jsonObject.put("Msg", "上传成功");
			jsonObject.put("Code", "00000000");
			jsonObject.put("fileUrl", "/"+FileType+"/"+filenames);
		}
		else
		{
			String delPath =uploadFolder + "/"+FileType+"/"+filenames;

			File delfile = new File(delPath);
			if (delfile.exists()) 
			{
				delfile.delete();
			} 

			jsonObject.put("Msg", "上传失败");
			jsonObject.put("Code", "11111111");
		}

		return jsonObject;
	}

	@UserLoginToken
	@GetMapping("getWaitAccessList")
	@ApiOperation(value="获取等待证据列表",notes="获取等待证据列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="page",value="当前页码",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="limit",value="每页条数",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="caseId",value="案件id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="userId",value="当事人id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getWaitAccessList(String page,String limit,@RequestParam("caseId")String caseId,String userId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(page!=null&&!page.equals("")&&limit!=null&&!limit.equals(""))
			{
				PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(limit));
				List<Access_wait> list=access_waitService.getWaitAccessList(caseId, userId);
				PageInfo<Access_wait> pageList = new PageInfo<Access_wait>(list);			
				jsonObject.put("data", pageList);
			}
			else
			{
				List<Access_wait> list=access_waitService.getWaitAccessList(caseId, userId);
				jsonObject.put("data", list);
			}

			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取等待证据列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取等待证据列表列表失败！");
		}
		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("addWaitAccess")
	@ApiOperation(value="添加等待证据到证据库",notes="添加等待证据到证据库",produces="application/json")
	public JSONObject addWaitAccess(@RequestBody @ApiParam(value = "要加入证据库的等待证据")Tjy_access tjy_access)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(tjy_accessService.addWaitTJYAccess(tjy_access.getAccessid(), uploadFolder, tjy_access.getFairworkerId()))
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "添加等待证据到证据库成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "添加等待证据到证据库失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "添加等待证据到证据库失败！");
		}

		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("operateTjblTemp")
	@ApiOperation(value="操作仲裁笔录临时表",notes="操作仲裁笔录临时表",produces="application/json")
	public JSONObject operateTjblTemp(@RequestBody @ApiParam(value = "仲裁笔录临时表记录")Aj_tjblls aj_tjblls)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Aj_tjblls existItem=aj_tjbllsService.getTjblTemp(Integer.toString(aj_tjblls.getAnjianId()), Integer.toString(aj_tjblls.getReciveuserId()));

			int flag=0;
			int itemId=0;

			if(existItem!=null)
			{
				itemId=existItem.getAj_tjblls_Id();
				flag=aj_tjbllsService.updateTjblTemp(aj_tjblls);
			}			
			else
			{
				flag=aj_tjbllsService.addTjblTemp(aj_tjblls);	
				itemId=aj_tjblls.getAj_tjblls_Id();
			}							

			if(flag>0)
			{
				jsonObject.put("itemId", itemId);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "操作仲裁笔录临时表成功！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "操作仲裁笔录临时表失败！");
		}

		return jsonObject;
	}

	@UserLoginToken
	@GetMapping("getTjblTempContent")
	@ApiOperation(value="获取临时发送笔录内容",notes="获取临时发送笔录内容",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="itemId",value="仲裁笔录临时表id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getTjblTempContent(@RequestParam("itemId")String itemId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String content=aj_tjbllsService.getTjblTempById(itemId);

			jsonObject.put("content", content);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取临时发送笔录内容成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取临时发送笔录内容失败！");
		}
		return jsonObject;
	}

	@UserLoginToken
	@PostMapping("addTjbl")
	@ApiOperation(value="添加仲裁笔录",notes="添加仲裁笔录",produces="application/json")
	public JSONObject addTjbl(@RequestBody @ApiParam(value = "仲裁笔录记录")Aj_tjbl aj_tjbl)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Boolean flag=aj_tjblService.addTjbl(aj_tjbl);			

			if(flag)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "添加仲裁笔录成功！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "添加仲裁笔录失败！");
		}

		return jsonObject;
	}
}
