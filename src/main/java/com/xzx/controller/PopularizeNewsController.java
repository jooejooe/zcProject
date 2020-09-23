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
import com.xzx.model.Newstype;
import com.xzx.model.Popilarizingnews;
import com.xzx.model.Popliarizingvideo;
import com.xzx.service.INewstypeService;
import com.xzx.service.IPopilarizingnewsService;
import com.xzx.service.IPopliarizingvideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/popularizeNews")
@Api(value="普法新闻相关接口",description="popularizeNews")
public class PopularizeNewsController {
	@Autowired
	IPopilarizingnewsService popilarizingnewsService;
	
	@Autowired
	IPopliarizingvideoService popliarizingvideoService;
	
	@Autowired
	INewstypeService newstypeService;
	
	/**
	 * 获取普法文字新闻列表
	 * @return
	 */
	@GetMapping("getPopularizeNewsList")
	@ApiOperation(value="普法新闻列表",notes="普法新闻列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数,查询条件(标题模糊)】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getPopularizeNewsList(String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(pageParams!=null&&pageParams.length>0)
			{
				PageHelper.startPage(Integer.parseInt(pageParams[0]),Integer.parseInt(pageParams[1]));
				
				List<Popilarizingnews> listNews=popilarizingnewsService.getPopNews(pageParams[2]);
				
				PageInfo<Popilarizingnews> page = new PageInfo<Popilarizingnews>(listNews);
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取普法新闻分页列表成功！");
			}
			else
			{
				List<Popilarizingnews> listNews=popilarizingnewsService.getPopNews("");
				
				jsonObject.put("data", listNews);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取普法新闻列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取司普法新闻列表失败！");
		}
		return jsonObject;
	}
	
	/**
	 * 根据对应id获取相应普法文字新闻详情
	 * @param id
	 * @return
	 */
	@GetMapping("getPopularizeNewsById")
	@ApiOperation(value="获取相应普法新闻id的新闻详情",notes="获取相应普法新闻id的新闻详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="普法新闻id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getPopularizeNewsById(
			@RequestParam("id") String id)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Popilarizingnews popilarizingnews=popilarizingnewsService.getNewsById(id);
			
			jsonObject.put("data", popilarizingnews);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取普法新闻详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取普法新闻详情失败！");
		}
		return jsonObject;
	}
	
	/**
	 * 获取普法视频新闻列表
	 * @return
	 */
	@GetMapping("getVideoNewsList")
	@ApiOperation(value="普法视频新闻列表",notes="普法视频新闻列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="newsType",value="普法视频新闻类别（-1：默认查第一项，页面首次加载；-3:查询全部；其他大于0的值，按需查询）",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数,查询条件(标题模糊)】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getVideoNewsList(
			@RequestParam("newsType") String newsType,String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Newstype> listNewsType=newstypeService.getNewsType("2");
			
			if(pageParams!=null&&pageParams.length>0)
			{
				PageHelper.startPage(Integer.parseInt(pageParams[0]),Integer.parseInt(pageParams[1]));
				
				List<Popliarizingvideo> listNews=new ArrayList<Popliarizingvideo>();
				
				if(newsType.equals("-1"))
				{
					String queryType=(listNewsType!=null&&listNewsType.size()>0)?Integer.toString(listNewsType.get(0).getNewsTypeId()):"-1";
					listNews=popliarizingvideoService.getNewsByParam(queryType,pageParams[2]);
				}
				else
				{
					listNews=popliarizingvideoService.getNewsByParam(newsType,pageParams[2]);
				}
				
				PageInfo<Popliarizingvideo> page = new PageInfo<Popliarizingvideo>(listNews);
				jsonObject.put("types", listNewsType);
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取普法视频新闻分页列表成功！");
			}
			else
			{
				List<Popliarizingvideo> listNews=new ArrayList<Popliarizingvideo>();
					
				if(newsType.equals("-1"))
				{
					String queryType=(listNewsType!=null&&listNewsType.size()>0)?Integer.toString(listNewsType.get(0).getNewsTypeId()):"-1";
					listNews=popliarizingvideoService.getNewsByParam(queryType,"");
				}
				else
				{
					listNews=popliarizingvideoService.getNewsByParam(newsType,"");
				}
				
				jsonObject.put("types", listNewsType);
				jsonObject.put("data", listNews);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取普法视频新闻列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取普法视频新闻列表失败！");
		}
		return jsonObject;
	}
	
	/**
	 * 根据对应id获取相应普法视频新闻详情
	 * @param id
	 * @return
	 */
	@GetMapping("getVideoNewsById")
	@ApiOperation(value="获取相应普法视频新闻id的新闻详情",notes="获取相应普法视频新闻id的新闻详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="普法视频新闻id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getVideoNewsById(
			@RequestParam("id") String id)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Popliarizingvideo popliarizingvideo=popliarizingvideoService.getVideoNewsById(id);
			
			jsonObject.put("data", popliarizingvideo);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取普法视频新闻详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取普法视频新闻详情失败！");
		}
		return jsonObject;
	}
}
