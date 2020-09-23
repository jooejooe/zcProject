package com.xzx.controller;


import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.UserLoginToken;
import com.xzx.model.Social;
import com.xzx.model.Socialrecoder;
import com.xzx.service.ISocialService;
import com.xzx.service.ISocialrecoderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-10-10
 */
@RestController
@RequestMapping("/social")
@Api(value="社矫打卡相关接口",description="social")
public class SocialController {
	@Autowired
    ISocialService socialService;
	
	@Autowired
    ISocialrecoderService socialrecoderService;
	
	@GetMapping("verifySocial")
	@ApiOperation(value="验证社矫人员信息",notes="根据身份证号验证社矫人员信息",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="SFZH",value="身份证号",dataType="String",required=true,paramType="query")
	})
	public JSONObject verifySocial(@RequestParam("SFZH")String SFZH)
	{
		JSONObject jsonObject=new JSONObject();
		
		try 
		{
			Social social=socialService.getSocialBySFZH(SFZH);
			
			if(social==null)
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "没有该人员的社矫信息！");
			}
			else if(social.getIsPunch()==1)
			{
				jsonObject.put("Code", "22222222");
				jsonObject.put("Msg", "该社矫人员无需打卡！");
			}
			else 
			{
				jsonObject.put("socialId", social.getSocialUserId());
				jsonObject.put("socialName", social.getSocialUserName());
				jsonObject.put("socialSFZH", SFZH);
				jsonObject.put("socialPhone", social.getSocialPhone());
				
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "社矫人员验证成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "验证人员社矫信息失败！");
		}
		return jsonObject;
	}
	
	/**
	 * 添加社矫打卡
	 * @param socialrecoder
	 * @return
	 */
	@UserLoginToken
	@PostMapping("addSocialRecorder")
	@ApiOperation(value="社矫打卡记录添加",notes="社矫打卡记录添加",produces="application/json")
	public JSONObject userLogin(@RequestBody @ApiParam(value = "社矫打卡信息") Socialrecoder socialrecoder)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			int flag=socialrecoderService.addSocialRecorder(socialrecoder);

			if(flag>0)
			{
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "社矫打卡成功！");
			}
			else 
			{
				jsonObject.put("Code", "11111111");
				jsonObject.put("Msg", "社矫打卡失败！");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "社矫打卡失败！");
		}	

		return jsonObject;
	}
}
