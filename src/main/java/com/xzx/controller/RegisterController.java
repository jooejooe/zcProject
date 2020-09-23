package com.xzx.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.OperationLogDetail;
import com.xzx.common.EncryptUtil;
import com.xzx.common.HttpDeal;
import com.xzx.enums.OperationType;
import com.xzx.enums.OperationUnit;
import com.xzx.model.Photoinfos;
import com.xzx.model.Register;
import com.xzx.service.IFairworkerService;
import com.xzx.service.IFingerprintinfosService;
import com.xzx.service.IRegisterService;
import com.xzx.service.ISocialService;
import com.xzx.viewModel.FingersInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.net.URL;
//import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
//import org.apache.cxf.endpoint.Client;
//import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 *  用户注册前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/register")
@Api(value="用户注册相关api",description="register")
public class RegisterController {
	@Autowired
	private IRegisterService registerService;

	@Autowired
	private IFairworkerService fairworkerService;

	@Value("${custom.faceUrl}")
	private String faceUrl;
	
	@Value("${custom.meetingUrl}")
	private String meetingUrl;
	
	@Value("${custom.ryUrl}")
	private String ryUrl;
	
	@Value("${custom.ryImageUrl}")
	private String ryImageUrl;

	@Autowired
	ISocialService socialService;

	@Autowired
	IFingerprintinfosService fingerprintinfosService;

	/**
	 * 注册
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	//@OperationLogDetail(detail = "用户注册{{SFZH}}",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.INSERT)
	@PostMapping("userRegister")
	@ApiOperation(value="用户注册",notes="根据身份证号进行人员注册",produces="application/json")
	public JSONObject userRegister(@RequestBody @ApiParam(value = "用户信息") Register register) throws Exception{
		System.out.println("register---->"+register);
		JSONObject jsonObject = new JSONObject();

		Register user=registerService.getRegisterUserBySFZH(register.getSFZH());
		System.out.println("user---->"+user);

		String phone=register.getTelephone();
		System.out.println("phone---->"+phone);

		int registerCount=registerService.registerCountByPhone(phone);
		System.out.println("registerCount---->"+registerCount);

		int fairworkerCount=fairworkerService.fairworkerCountByPhone(phone);
		System.out.println("fairworkerCount---->"+fairworkerCount);

		if(user!=null)
		{
			System.out.println("该身份证号已注册");
			jsonObject.put("Code", "22222222");
			jsonObject.put("Msg", "该身份证号已注册！");
		}
		else if(registerCount>0||fairworkerCount>0)
		{
			System.out.println("该手机号码已注册");
			jsonObject.put("Code", "33333333");
			jsonObject.put("Msg", "该手机号码已注册！");
		}
		else
		{
			String password=EncryptUtil.encryptMD5(register.getPassword());			
			register.setPassword(password);

			int flag=registerService.registerUser(register);
			System.out.println("flag(保存结果,应该为1)--->"+flag);
			if(flag==0)
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "注册失败！");
			}
			else
			{
//				String flag_MeetingUser=addUserinfo(register.getRealName(), "123456aa", "", 0, "2","", "", "", "", "3025495AEE146DA3864AB81BAAF79A3E", 0);
//				
//				if(flag_MeetingUser.equals("0000"))
//				{
//					jsonObject.put("meetingUserAdd", "好事通用户添加成功");
//				}
//				else
//				{
//					jsonObject.put("meetingUserAdd", "好事通用户添加失败！错误码："+flag_MeetingUser);
//				}
					
				/*//当注册时有图片时，调用接口，上传百度人脸库
				if(register.getImage()!=null&&!register.getImage().equals(""))
					uploadFace(Integer.toString(register.getUserId()),register.getImage());*/

				//注册融云接口
				System.out.println("保存成功");
				Map<String, Object> map=ryRegister(register.getTelephone(), register.getRealName());
				if(Integer.parseInt(map.get("code").toString())==200)
				{
					//更新用户融云信息
					System.out.println("更新用户融云信息");
					registerService.updateRYInfo(register.getTelephone(), map.get("token").toString(), register.getTelephone());
					System.out.println("成功!");
					jsonObject.put("userinfo", register);
					jsonObject.put("registerId", register.getUserId());
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "注册成功！");
				}			
			}
		}

		return jsonObject;
	}

	/**
	 * 更新注册信息
	 * @param request
	 * @return
	 */
	@OperationLogDetail(detail = "更新注册",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.INSERT)
	@PostMapping("updateRegister")
	@ApiOperation(value="更新注册信息",notes="更新相应人员的注册信息",produces="application/json")
	public JSONObject updateRegister(@RequestBody @ApiParam(value = "用户信息") Register register){

		int flag=registerService.updateRegisterUser(register);

		JSONObject jsonObject = new JSONObject();

		if(flag==0)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "修改失败！");
		}
		else
		{
			Register user=registerService.getRegisterUserById(Integer.toString(register.getUserId()));

			jsonObject.put("userinfo", user);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "修改成功！");

		}

		return jsonObject;
	}

	/**
	 * 根据身份证号获取用户信息
	 * @return
	 */
	@GetMapping("getUserInfoBySFZH")
	@ApiOperation(value="获取用户信息",notes="根据身份证号获取用户信息",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="SFZH",value="身份证号",dataType="String",required=true,paramType="query")
	})
	public JSONObject getUserInfoBySFZH(@RequestParam("SFZH") String SFZH){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Register user=registerService.getRegisterUserBySFZH(SFZH);
			jsonObject.put("data", user);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取用户信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取用户信息失败！");
		}		

		return jsonObject;
	}

	/**
	 * 判断相应手机号是否已注册
	 * @param Phone
	 * @return
	 */
	@GetMapping("getRegisterByPhone")
	@ApiOperation(value="判断相应手机号是否已注册",notes="判断相应手机号是否已注册",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="Phone",value="手机号码",dataType="String",required=true,paramType="query")
	})
	public JSONObject getRegisterByPhone(@RequestParam("Phone") String Phone){
		JSONObject jsonObject = new JSONObject();

		try 
		{
			int registerCount=registerService.registerCountByPhone(Phone);

			int fairworkerCount=fairworkerService.fairworkerCountByPhone(Phone);

			if(registerCount>0||fairworkerCount>0)
			{
				jsonObject.put("Code", "111111111");
				jsonObject.put("Msg", "该手机号码已注册！");
			}
			else
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "该手机号码可以注册！");
			}
		}
		catch(Exception e)
		{
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取手机号是否注册结果失败！");
		}

		return jsonObject;
	}

	/**
	 * 终端机刷身份证登录
	 * @param SFZH
	 * @return
	 */
	@PostMapping("userLogin")
	@ApiOperation(value="用户登录",notes="【身份证号登录方式】用户登录",produces="application/json")
	public JSONObject userLogin(@RequestBody @ApiParam(value = "用户信息【只需传身份证号】") Register register)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Register user=registerService.getRegisterUserBySFZH(register.getSFZH());

			if(user!=null)
			{
				//更新最后一次登录时间
				int flag=registerService.updateLoginTime(register.getSFZH());

				if(flag>0)
				{
					jsonObject.put("userinfo", user);
					jsonObject.put("Code", "00000000");
					jsonObject.put("Msg", "登录成功！");
				}
				else 
				{
					jsonObject.put("Code", "11111111");
					jsonObject.put("Msg", "登录失败！");
				}
			}
			else 
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "用户不存在，请先注册！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "登录失败！");
		}	

		return jsonObject;
	}

	/**
	 * 获取可用用户的指纹列表
	 * @return
	 */
	@PostMapping("/getAllFingerprint")
	@ApiOperation(value="获取所有可用用户指纹信息",notes="获取所有可用用户指纹信息",produces="application/json")
	public JSONObject getAllFingerprint(@ModelAttribute Photoinfos photoInfo)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			String SFZH=photoInfo.getSFZH();

			List<FingersInfo> list=new ArrayList<FingersInfo>();

			if(SFZH==null||SFZH.equals(""))//获取已注册用户的所有指纹列表
				list=registerService.getAllFingerprint();
			else
				list=socialService.getAllSocialFinger(SFZH);

			jsonObject.put("data", list);
			jsonObject.put("Count", list.size());
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取指纹信息列表成功！");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取指纹信息列表失败！");
		}

		return jsonObject;
	}

	/**
	 * 更新注册用户指纹信息
	 * @param request
	 * @return
	 */
	@OperationLogDetail(detail = "更新注册用户指纹信息",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.INSERT)
	@PostMapping("updateRegisterFinger")
	@ApiOperation(value="更新注册用户指纹信息",notes="更新注册用户指纹信息",produces="application/json")
	public JSONObject updateRegisterFinger(@RequestBody @ApiParam(value = "用户信息") Register register){
		JSONObject jsonObject = new JSONObject();
		
		try 
		{
			Register user=registerService.getRegisterUserById(Integer.toString(register.getUserId()));
			
			int flag=registerService.updateRegisterFingerById(register.getAddress(), Integer.toString(register.getUserId()),user.getSFZH());			
			
			if(flag==0)
			{		
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "修改失败！");
			}
			else
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "修改成功！");			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "修改失败！");
		}

		return jsonObject;
	}

	//上传人脸图片到百度人脸库
	public void uploadFace(String SFZH,String faceImage)//String SFZH,String faceImage
	{
		HttpDeal hd = new HttpDeal();
		/*		String reponse=hd.get(faceUrl+"?departType=6&areaId=3");
		System.out.println(reponse);*/

		Map<String,String> map = new HashMap<String,String>();
		map.put("userId",SFZH);
		map.put("image", faceImage);
		String response=hd.post(faceUrl,map);
		System.out.println(response);
	}
	
	/**
	 * 调取好事通接口，添加用户
	 * @param userName
	 * @param passpwd
	 * @param nickName
	 * @param departID
	 * @param adminRole
	 * @param sex
	 * @param mobile
	 * @param telePhone
	 * @param email
	 * @param keyCode
	 * @param password_type
	 * @return
	 * @throws Exception
	 */
	public String addUserinfo(String userName, String passpwd, String nickName, Integer departID, String adminRole,
			String sex, String mobile, String telePhone, String email, String keyCode, Integer password_type)
	      throws Exception {
			String method = "addUserinfo";
			
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();    
			Client client =  dcf.createClient(new URL(meetingUrl)); 
			Object[] object ={userName,passpwd, nickName,departID, adminRole,
								sex, mobile,telePhone,email,keyCode,password_type};
			Object[] res = client.invoke(method, object);	
			
			Document doc = DocumentHelper.parseText(res[0].toString() + "");
            
			// 获取根节点
            Element rootElt = doc.getRootElement();
            
            String code=rootElt.elementText("code");
			
			return code;
	  }
	
	/**
	 * 根据openid获取用户信息
	 * @param openId
	 * @return
	 */
	@GetMapping("getRegisterByOpenId")
	@ApiOperation(value="根据openid获取用户信息",notes="根据openid获取用户信息",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="openId",value="openId",dataType="String",required=true,paramType="query")
	})
	public JSONObject getRegisterByOpenId(@RequestParam("openId") String openId){
		JSONObject jsonObject = new JSONObject();
		System.out.println("openId--->"+openId);
		try 
		{
			Register register=registerService.getRegisterByOpenId(openId);

			if(register!=null)
			{
				System.out.println("register是空的!!!没有通过openid查到绑定信息");
				jsonObject.put("data", register);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "该openid已绑定用户！");
			}
			else
			{
				System.out.println("register里的openid被用过了");
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "该openid没有绑定用户！");
			}
		}
		catch(Exception e)
		{
			System.out.println("查询报错了~~~trycatch抓到了错误信息~~~~~");
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取openid绑定用户失败！");
		}

		return jsonObject;
	}
	
	@OperationLogDetail(detail = "更新用户OpenId信息",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.INSERT)
	@PostMapping("updateRegisterOpenId")
	@ApiOperation(value="更新用户OpenId信息",notes="更新用户OpenId信息",produces="application/json")
	public JSONObject updateRegisterOpenId(@RequestBody @ApiParam(value = "用户信息") Register register){
		JSONObject jsonObject = new JSONObject();
		
		try 
		{
			int flag=registerService.updateOpenId(register.getOpenid(), register.getSFZH());
			
			if(flag==0)
			{		
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "更新用户OpenId信息失败！");
			}
			else
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "更新用户OpenId信息成功！");			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "更新用户OpenId信息失败！");
		}

		return jsonObject;
	}
	
	
	public Map<String, Object> ryRegister(String tel,String name)
	{
		HttpDeal hd = new HttpDeal();
		Map<String,String> map = new HashMap<String,String>();
		map.put("id",tel);
		map.put("name", name);
		map.put("portrait", ryImageUrl);
		String response=hd.post(ryUrl,map);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map2=(Map<String, Object>) JSON.parse(response);
		
		return map2;
	}

}
