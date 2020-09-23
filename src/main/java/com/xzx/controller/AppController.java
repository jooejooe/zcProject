package com.xzx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.xzx.annotation.UserLoginToken;
import com.xzx.common.EncryptUtil;
import com.xzx.common.HttpDeal;
import com.xzx.common.RedisUtil;
import com.xzx.common.TokenUtil;
import com.xzx.model.*;
import com.xzx.service.*;
import com.xzx.viewModel.AddAccessInfos;
import com.xzx.viewModel.AppLoginInfo;
import com.xzx.viewModel.ItemDetailInfo;
import com.xzx.viewModel.SpeedInfos;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.auth0.jwt.exceptions.TokenExpiredException;

@RestController
@RequestMapping("/app")
@Api(value="手机端相关接口",description="app")
public class AppController {
	@Autowired
    IFairworkerService fairworkerService;

	@Autowired
    IRegisterService registerService;

	@Autowired
    ISpeedService speedService;

	@Autowired
    IJustService justService;

	@Autowired
    ILawhelpService lawhelpService;

	@Autowired
    IAuthenticService authenticService;

	@Autowired
    IAppraisalService appraisalService;

	@Autowired
    ISubscribeService subscribeService;

	@Autowired
    IAccessService accessService;
	
	@Autowired
    IRongworkerService rongworkerService;
	
	@Autowired
    IRegionService regionService;
	
	@Autowired
    IDepartmentService departmentService;
	
	@Value("${custom.ryGetUserOnlineStateUrl}")
	private String ryGetUserOnlineStateUrl;
	
	@Autowired
	private IBiddingmanagersmoneyService biddingmanagersmoneyService;
	
	@Value("${custom.tokenExpiretime}")
	public long EXPIRE_TIME;
	
	@Resource
    RedisUtil redisUtil;

	/**
	 * app登录
	 * @param appLoginInfo
	 * @return
	 */
	@PostMapping("appLogin")
	@ApiOperation(value="app用户登录",notes="app用户登录",produces="application/json")
	public JSONObject appLogin(@RequestBody @ApiParam(value = "登录信息") AppLoginInfo appLoginInfo, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		
		String[] workerTypeNames={"公证员","律师","人民调解员","法律服务人员 ","司法鉴定员","司法所人员","行政机关人员","管理员"};

		try 
		{
			String logName=appLoginInfo.getLogName();
			String password= EncryptUtil.encryptMD5(appLoginInfo.getPassword());

			Fairworker fairworker=fairworkerService.appLogin(logName, password);

			Register register=registerService.appLogin(logName, password);

			if(fairworker!=null)
			{
				//生成token
				String name=fairworker.getWorkerName()==null?"":fairworker.getWorkerName();
				String userid=Integer.toString(fairworker.getFairWorkerId());
				
				String token= TokenUtil.sign(name,userid,"1",EXPIRE_TIME);
				response.addHeader("token", token);
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("key", userid+","+"1");
				map.put("value", token);
				redisUtil.add(map);
				//jsonObject.put("token", token);
				
				jsonObject.put("userid", fairworker.getFairWorkerId());
				jsonObject.put("username", fairworker.getWorkerName()==null?"":fairworker.getWorkerName());
				jsonObject.put("head", fairworker.getImage()==null?"":fairworker.getImage());
				jsonObject.put("Code", "0000000");
				jsonObject.put("Msg", "登录成功！");
				jsonObject.put("userType", "1");
				jsonObject.put("regionId", fairworker.getRegionId());
				jsonObject.put("WorkerType", fairworker.getWorkerType());
				jsonObject.put("workerTelephone", fairworker.getTelephone());
				jsonObject.put("workTypeName", workerTypeNames[fairworker.getWorkerType()]);
				jsonObject.put("workerSFZH", fairworker.getSFZH());
				jsonObject.put("rytoken", fairworker.getRytoken());
			}
			else if(register!=null)
			{
				int flag=registerService.updateLoginTime(register.getSFZH());

				//修改最后一次登录时间
				if(flag>0)
				{
					//生成token
					String name=register.getRealName()==null?"":register.getRealName();
					String userid=Integer.toString(register.getUserId());
					
					String token= TokenUtil.sign(name,userid,"0",EXPIRE_TIME);
					response.addHeader("token", token);
					//jsonObject.put("token", token);
					
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("key", userid+","+"0");
					map.put("value", token);
					redisUtil.add(map);
					
					jsonObject.put("userid", userid);
					jsonObject.put("SFZH", register.getSFZH());
					jsonObject.put("username", register.getRealName()==null?"":register.getRealName());
					jsonObject.put("head", register.getImage()==null?"":register.getImage());
					jsonObject.put("address", register.getAddress()==null?"":register.getAddress());
					jsonObject.put("email", register.getEmail()==null?"":register.getEmail());
					jsonObject.put("qq", register.getQQ()==null?"":register.getQQ());
					jsonObject.put("Code", "0000000");
					jsonObject.put("Msg", "登录成功！");
					jsonObject.put("userType", "0");
					jsonObject.put("userTelephone", register.getTelephone());
					jsonObject.put("rytoken", register.getRytoken()==null?"":register.getRytoken());
					jsonObject.put("dlzh", register.getLoginzh()==null?"":register.getLoginzh());
					jsonObject.put("czaddress", register.getCzaddress()==null?"":register.getCzaddress());
					jsonObject.put("idcardoverdate", register.getIdcardoverdate()==null?"":register.getIdcardoverdate());
				}
			}
			else
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "用户名密码错误！");
				/*				int count=fairworkerService.getLogNameCount(logName);

				if(count==0)
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "用户名不存在！");
				}
				else
				{
					jsonObject.put("Code", "22222222");
					jsonObject.put("Msg", "用户名密码错误！");
				}*/
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "33333333");
			jsonObject.put("Msg", "登录失败！");
		}

		return jsonObject;
	}

	/**
	 * 修改app用户密码
	 * @return
	 */
	@UserLoginToken
	@PostMapping("updatePassword")
	@ApiOperation(value="app用户修改密码",notes="app用户修改密码",produces="application/json")
	public JSONObject updatePassword(@RequestBody @ApiParam(value = "用户信息") AppLoginInfo appLoginInfo)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String oldPassword= EncryptUtil.encryptMD5(appLoginInfo.getPassword());
			String newPassword= EncryptUtil.encryptMD5(appLoginInfo.getNewPassword());
			String userId=appLoginInfo.getUserId();

			String userType=appLoginInfo.getUserType();

			int rightPass=0;

			//判断当前用户的原密码输入是否正确，若不正确返回，正确才能继续修改密码
			if(userType.equals("0"))
			{
				//普通用户
				rightPass=registerService.testUserPassword(userId, oldPassword);
			}
			else
			{
				//工作人员
				rightPass=fairworkerService.testUserPassword(userId, oldPassword);
			}

			if(rightPass>0)
			{
				int updateFlag=0;

				//更新用户密码
				if(userType.equals("0"))
				{
					updateFlag=registerService.updatePassword(userId, newPassword);
				}
				else
				{
					updateFlag=fairworkerService.updatePassword(userId, newPassword);
				}				

				if(updateFlag>0)
				{
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "密码修改成功！");
				}
				else 
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "密码修改失败！");
				}
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "原密码输入不正确！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "密码修改失败！");
		}

		return jsonObject;
	}

	/**
	 * app工作人员获取在线审批列表
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getOnlineDisposeItem")
	@ApiOperation(value="app工作人员获取在线审批列表",notes="获取在线审批列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="State",value="当前要查看的事项状态(在线办理：0 待审批  1 审批中 2 审核通过 3 补充材料 4 已撤销 5 未通过;预约：0 待确定 1 预约成功 2 预约失败 3 更改预约时间 4 补充材料)",dataType="String",required=true,paramType="query",allowableValues="0,1,2,3,4,5"),
		@ApiImplicitParam(name="itemType",value="事项类型（0-在线申办；1-预约）",dataType="String",required=true,paramType="query",allowableValues="0,1"),
		@ApiImplicitParam(name="userId",value="当前用户id",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="userType",value="当前用户类型(0 公证员 1 律师 2 人民调解员 3 法律服务人员 4 司法鉴定员 5 司法所人员 6 行政机关人员,7管理员)",dataType="String",required=true,paramType="query",allowableValues="0,1,2,3,4,5,6,7")
	})
	public JSONObject getOnlineDisposeItem(
			@RequestParam("State") String State,
			@RequestParam("itemType") String itemType,
			@RequestParam("userId") String userId,
			@RequestParam("userType") String userType)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String modelTypeStr="";
			
			if(itemType.equals("0"))
			{
				switch (userType) {
				case "0":
					modelTypeStr="0";
					break;
				case "1":
					modelTypeStr="-1";
					break;
				case "2":
					modelTypeStr="1";
					break;
				case "3":
					modelTypeStr="3";
					break;
				case "4":
					modelTypeStr="2";
					break;
				default:
					modelTypeStr="0,1,2,3";
					break;
				}			
			}				
			else		//办理类别(0 公证办理 1 人民调解 2 司法鉴定 3 法律援助 4 预约公证办理 5  预约人民调解 6 预约司法鉴定 7 预约法律援助 8 预约律师)
			{
				switch (userType) {
				case "0":
					modelTypeStr="4";
					break;
				case "1":
					modelTypeStr="8";
					break;
				case "2":
					modelTypeStr="5";
					break;
				case "3":
					modelTypeStr="7";
					break;
				case "4":
					modelTypeStr="6";
					break;
				default:
					modelTypeStr="4,5,6,7,8";
					break;
				}
			}				

			List<Map<String, Object>> list=speedService.getSpeedList(userId, State, modelTypeStr);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取在线审批列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取在线审批列表失败！");
		}
		return jsonObject;
	}

	/**
	 * app普通用户获取已申办事项的处理进度
	 * @param modelType
	 * @param itemType
	 * @param userId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getItemSchedule")
	@ApiOperation(value="app普通用户获取已申办事项的处理进度",notes="获取已申办事项处理进度列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="modelType",value="事项类型(0 公证办理  1 人民调解 2 司法鉴定 3 法律援助 4 律师预约)",dataType="String",required=true,paramType="query",allowableValues="0,1,2,3,4"),
		@ApiImplicitParam(name="itemType",value="事项类型（0-在线申办；1-预约）",dataType="String",required=true,paramType="query",allowableValues="0,1"),
		@ApiImplicitParam(name="userId",value="当前用户id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getItemSchedule(
			@RequestParam("modelType") String modelType,
			@RequestParam("itemType") String itemType,
			@RequestParam("userId") String userId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			//0 公证办理 1 人民调解 2 司法鉴定 3 法律援助 4 预约公证办理 5  预约人民调解 6 预约司法鉴定 7 预约法律援助 8 预约律师
			String[] onlineStateArray={"0","1","2","3","-1"};
			String[] orderStateArray={"4","5","6","7","8"};//{"4","6","7","8","5"};

			String modelTypeStr="";

			if(itemType.equals("0"))
			{
				modelTypeStr=onlineStateArray[Integer.parseInt(modelType)];
			}
			else
			{
				modelTypeStr=orderStateArray[Integer.parseInt(modelType)];
			}

			List<Map<String, String>> list=speedService.getScheduleList(userId, modelTypeStr);

			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取申办事项查询列表成功！");				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取在线审批列表失败！");
		}
		return jsonObject;
	}

	/**
	 * 获取申办事项详情
	 * @param userId
	 * @param modelType
	 * @param speedId
	 * @param modelId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getItemInfoById")
	@ApiOperation(value="获取申办事项详情",notes="获取申办事项详情【司法鉴定(NextMatterType=14:对方当事人信息，15=对方当事人机构)】",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="modelType",value="事项类型(0 公证办理 1 人民调解 2 司法鉴定 3 法律援助 4 预约公证办理 5 预约人民调解 6 预约司法鉴定 7 预约法律援助 8 预约律师)",dataType="String",required=true,paramType="query",allowableValues="0,1,2,3,4,5,6,7,8"),
		@ApiImplicitParam(name="speedId",value="进度id",dataType="String",required=true,paramType="query"),
		/*@ApiImplicitParam(name="userId",value="当前用户id",dataType="String",required=true,paramType="query"),*/
		@ApiImplicitParam(name="modelId",value="业务id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getItemInfoById(
			//@RequestParam("userId")String userId,
			@RequestParam("modelType")String modelType,@RequestParam("speedId")String speedId,@RequestParam("modelId")String modelId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<ItemDetailInfo> list=new ArrayList<ItemDetailInfo>();
			ItemDetailInfo itemDetailInfo=new ItemDetailInfo();

			if(modelType.equals("0"))//在线公证办理
			{
				list=justService.getItemInfoById(modelId);//, userId
				if(list!=null&&list.size()>0)
				{
					Biddingmanagersmoney moneyInfo=biddingmanagersmoneyService.getMatterMoneyInfo(Integer.toString(list.get(0).getBiddingMattersId()));
					
					if(moneyInfo!=null)
						jsonObject.put("allMoney", moneyInfo.getMoney());
					else
						jsonObject.put("allMoney", 0);
				}
			}			
			else if(modelType.equals("1"))//人民调解
				list=authenticService.getItemInfoById(modelId);//, userId
			else if(modelType.equals("2"))//司法鉴定(NextMatterType=14:对方当事人信息，15=对方当事人机构)
				list=appraisalService.getItemInfoById(modelId);//, userId
			else if(modelType.equals("3"))//在线法律援助
				list=lawhelpService.getItemInfoById(modelId);//, userId
			else//各类预约
				list=subscribeService.getItemInfoById(modelId);//, userId

			if(list!=null&&list.size()>0)
				itemDetailInfo=list.get(0);

			jsonObject.put("itemInfo", itemDetailInfo);
			
			Speed speed=speedService.getSpeedById(speedId);

			List<Access> listAccess=accessService.getAccessByItemId(modelId, modelType);

			jsonObject.put("speed", speed);
			
			jsonObject.put("access", listAccess);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取申办事项详情成功！");	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取申办事项详情失败！");	
		}
		return jsonObject;
	}

	/**
	 * 更新申请事项进度
	 * @param request
	 * @return
	 */
	@UserLoginToken
	@PostMapping("updateSpeed")
	@ApiOperation(value="修改申请事项状态及其他相关信息",notes="修改申请事项状态及其他相关信息",produces="application/json")
	public JSONObject updateSpeed(@RequestBody @ApiParam(value = "最新进度信息【state——在线办理：0 待审批  1 审批中 2 审核通过 3 补充材料 4 已撤销 5 未通过;预约：0 待确定 1 预约成功 2 预约失败 3 更改预约时间  4 补充材料】") SpeedInfos speedInfos){
		JSONObject jsonObject = new JSONObject();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try 
		{
			//当要修改预约时间时，判断之前的预约时间是否已经过期，过期则不许修改预约时间
			Speed speed=speedService.getSpeedById(Integer.toString(speedInfos.getSpeed().getSpeedId()));

			if(speed.getModelType()>=4&&speed.getModelType()<=8&&speedInfos.getSpeed().getState()==3)
			{
				Subscribe subscribe=subscribeService.getSubscribeById(Integer.toString(speed.getModelId()));

				String oldTime=subscribe.getSubscribe();
				Date dtOld = df.parse(oldTime);

				Date dtNew=df.parse(df.format(new Date()));

				if(dtNew.getTime()>=dtOld.getTime())
				{
					jsonObject.put("Code", "22222222");
					jsonObject.put("Msg", "您的预约时间已过，请重新预约！");
				}
				else
				{
					Boolean flag=speedService.updateSpeedState(speedInfos.getSpeed(),speedInfos.getNewTime());

					if(flag)
					{
						jsonObject.put("Code", "00000000");
						jsonObject.put("Msg", "更新进度成功！");
					}
					else
					{
						jsonObject.put("Code", "11111111");
						jsonObject.put("Msg", "更新进度失败！");
					}
				}
			}
			else 
			{
				Boolean flag=speedService.updateSpeedState(speedInfos.getSpeed(),speedInfos.getNewTime());

				if(flag)
				{
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "更新进度成功！");
				}
				else
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "更新进度失败！");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "更新进度失败！");
		}

		return jsonObject;
	}

	/**
	 * 补充材料
	 * @param addAccessInfos
	 * @return
	 */
	@UserLoginToken
	@PostMapping("addAccess")
	@ApiOperation(value="app普通用户补充材料",notes="app普通用户补充材料",produces="application/json")
	public JSONObject addAccess(@RequestBody @ApiParam(value = "补充材料信息") AddAccessInfos addAccessInfos){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			int flag=accessService.insertBatchAccess(addAccessInfos.getListAccess(), Integer.parseInt(addAccessInfos.getBusinessId()), Integer.parseInt(addAccessInfos.getModelType()),1);

			if(flag>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "补充材料成功！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "补充材料失败！");
		}

		return jsonObject;
	}

	/**
	 * 添加评价
	 * @param speed
	 * @return
	 */
	@UserLoginToken
	@PostMapping("addEvaluate")
	@ApiOperation(value="app普通用户申办事项评价",notes="app普通用户申办事项评价",produces="application/json")
	public JSONObject addEvaluate(@RequestBody @ApiParam(value = "评价事项相关信息") Speed speed)
	{
		JSONObject jsonObject = new JSONObject();

		try
		{
			int flag=speedService.updateSpeed(speed);

			if(flag>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "评价成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "评价失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "评价失败！");
		}

		return jsonObject;
	}
	
	@UserLoginToken
	@PostMapping("updateRYInfo")
	@ApiOperation(value="更新融云信息",notes="更新融云信息",produces="application/json")
	public JSONObject updateRYInfo(@RequestBody @ApiParam(value = "更新融云信息") Register register)
	{
		JSONObject jsonObject = new JSONObject();

		try
		{
			int flag=registerService.updateRYInfo(register.getTelephone(), register.getRytoken(), register.getRyuserid());

			if(flag>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "更新融云信息成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "更新融云信息失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "更新融云信息失败！");
		}

		return jsonObject;
	}
	
	/**
	 * 获取融云工作人员列表
	 * @param regionId
	 * @param departmentId
	 * @param typeId
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getRYWorkerList")
	@ApiOperation(value="获取融云工作人员列表",notes="获取融云工作人员列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="regionId",value="区域id",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="departmentId",value="机构id",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="typeId",value="类别id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getRYWorkerList(
			String regionId,
			String departmentId,
			String typeId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{			
			List<Rongworker> list=rongworkerService.getRyWorkerList(regionId, typeId, departmentId);
			
			for (int i = 0; i < list.size(); i++) {
				Rongworker rongworker=list.get(i);
				
				if(!rongworker.getWorkPhone().equals("")&&rongworker.getWorkPhone()!=null)
				{
					Map<String, Object> map=getRyOnlineState(rongworker.getWorkPhone());
					
					if(Integer.parseInt(map.get("code").toString())==200)
					{
						rongworker.setIsOnline(map.get("status").toString());
					}
					else
					{
						rongworker.setIsOnline("-1");
					}
				}
				else
				{
					rongworker.setIsOnline("-1");
				}
					
			}
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取融云工作人员列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取融云工作人员列表失败！");
		}
		return jsonObject;
	}
	
	/**
	 * 获取融云区域列表
	 * @return
	 */
	@UserLoginToken
	@GetMapping("getRYRegionList")
	@ApiOperation(value="获取融云区域列表",notes="获取融云区域列表",produces="application/json")
	public JSONObject getRYRegionList()
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{			
			List<Region> list=regionService.getRYRegionList();
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取融云区域列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取融云区域列表失败！");
		}
		return jsonObject;
	}
	
	@UserLoginToken
	@GetMapping("getRYDepartmentList")
	@ApiOperation(value="获取融云机构列表",notes="获取融云机构列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="regionId",value="区域id",dataType="String",required=false,paramType="query")
	})
	public JSONObject getRYDepartmentList(String regionId)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{			
			List<Department> list=departmentService.getRYDepartList(regionId);
			jsonObject.put("data", list);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取融云机构列表成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取融云机构列表失败！");
		}
		return jsonObject;
	}
	
	public Map<String, Object> getRyOnlineState(String tel)
	{
		HttpDeal hd = new HttpDeal();
		String response=hd.get(ryGetUserOnlineStateUrl+tel);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map2=(Map<String, Object>) JSON.parse(response);
		
		return map2;
	}
	
	@UserLoginToken
	@PostMapping("resetPassword")
	@ApiOperation(value="重置密码",notes="重置密码",produces="application/json")
	public JSONObject resetPassword(@RequestBody @ApiParam(value = "登录信息") AppLoginInfo appLoginInfo){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String logName=appLoginInfo.getLogName();
			String password= EncryptUtil.encryptMD5(appLoginInfo.getPassword());

			int flag=registerService.resetPassword(password, logName);
			
			if(flag>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "重置密码成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "重置密码成功！");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "33333333");
			jsonObject.put("Msg", "登录失败！");
		}

		return jsonObject;
	}
	
//	/**
//	 * 根据token获取用户相关信息
//	 * @param token
//	 * @return
//	 */
//	@ApiIgnore
//	@GetMapping("getUserInfoByToken")
//	public JSONObject getUserInfoByToken(HttpServletRequest httpServletRequest)//@RequestParam("token")String token
//	{
//		JSONObject jsonObject = new JSONObject();
//
//		try 
//		{
//			String token = httpServletRequest.getHeader("token");
//			String userId="";
//			
//			try {				
//				TokenUtil.verity(token);
//				
//				userId=JWT.decode(token).getClaim("userId").asString();
//				jsonObject.put("userId", userId);
//				jsonObject.put("Code", "00000000");
//				jsonObject.put("Msg", "根据token获取用户信息成功！");
//			} 
//			catch (TokenExpiredException e) {
//				userId=JWT.decode(token).getClaim("userId").asString();
//				
//				jsonObject.put("userId", userId);
//				jsonObject.put("Code", "22222222");
//				jsonObject.put("Msg", "token过期！");
//			}
//			catch (JWTDecodeException e) {			
//				jsonObject.put("userId", userId);
//				jsonObject.put("Code", "33333333");
//				jsonObject.put("Msg", "token不存在！");
//			}
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//			
//			jsonObject.put("userId", "");
//			jsonObject.put("Code", "11111111");
//			jsonObject.put("Msg", "根据token获取用户信息失败！");
//		}
//		return jsonObject;
//	}
	
	/**
	 * 根据token获取用户相关信息
	 * @param token
	 * @return
	 */
	@ApiIgnore
	@GetMapping("getUserInfoByToken")
	public JSONObject getUserInfoByToken(HttpServletRequest httpServletRequest)//@RequestParam("token")String token
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String token = httpServletRequest.getHeader("token");
			
			// 获取 token 中的 user id
			String userId = "";
			String userType = null;

			try
			{
				//从header的token中获取userId及用户类型
				userId=JWT.decode(token).getClaim("userId").asString();
				userType=JWT.decode(token).getClaim("userType").asString();
			}
			catch(JWTDecodeException e)
			{						
				jsonObject.put("userId", userId);
				jsonObject.put("Code", "33333333");
				jsonObject.put("Msg", "token不存在！");
				
				return jsonObject;
			}

			String redisKey=userId+","+userType;
			
			if(redisUtil.isHaveKey(redisKey))
			{
				if(redisUtil.getValue(redisKey).equals(token))
				{
					jsonObject.put("userId", userId);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "根据token获取用户信息成功！");
				}
				else
				{					
					jsonObject.put("userId", userId);
					jsonObject.put("Code", "33333333");
					jsonObject.put("Msg", "token不存在！");
				}
			}
			else
			{
				jsonObject.put("userId", userId);
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "token过期！");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
			jsonObject.put("userId", "");
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "根据token获取用户信息失败！");
		}
		return jsonObject;
	}
	
	@UserLoginToken
	@PostMapping("logout")
	@ApiOperation(value="退出登录",notes="退出登录",produces="application/json")
	public JSONObject logout(HttpServletRequest httpServletRequest){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String token = httpServletRequest.getHeader("token");
			String userId;
			String userType;
			userId=JWT.decode(token).getClaim("userId").asString();
			userType=JWT.decode(token).getClaim("userType").asString();
			
			redisUtil.delete(userId+","+userType);
			
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "退出登录成功！");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "退出登录失败！");
		}

		return jsonObject;
	}
}