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
import com.xzx.model.Examineenotice;
import com.xzx.model.News;
import com.xzx.model.Newstype;
import com.xzx.service.IExamineenoticeService;
import com.xzx.service.INewsService;
import com.xzx.service.INewstypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-10-08
 */
@RestController
@RequestMapping("/news")
@Api(value="新闻相关接口",description="news")
public class NewsController {
	@Autowired
	INewsService newsService;

	@Autowired
	INewstypeService newstypeService;
	
	@Autowired
	IExamineenoticeService examineenoticeService;
	
	//edit by helen:终端机已推荐，已启用；pc：推荐显示已推荐，已启用，司法要闻显示已启用全部
	@GetMapping("getLawNewsList")
	@ApiOperation(value="司法行政要闻新闻列表",notes="司法行政要闻新闻列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",value="设备唯一令牌【仅终端机需要，pc传空】",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="newsType",value="司法行政新闻类别【<br/>终端机：（-1：默认查第一项，页面首次加载；其他大于0的值，按需查询）；<br/>PC:-1获取类别中第一项新闻集合；-3查全部；其他大于0的值返回对应类别的新闻集合】",dataType="String",required=true,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数,查询条件(搜索条件,推荐状态<0已推荐；-1全部>)】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getLawNewsList(
			String token,
			@RequestParam("newsType") String newsType,String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Newstype> listNewsType=newstypeService.getNewsType("0");
			jsonObject.put("types", listNewsType);
			
			List<News> listNews=new ArrayList<News>();
			
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				String titleSearch=pageParams[2];//新闻标题模糊搜索条件
				int recommendType=Integer.parseInt(pageParams[3]);//推荐状态
				int news_Type=Integer.parseInt(newsType);//新闻类型
				
				if(news_Type==-1)
				{
					news_Type=(listNewsType!=null&&listNewsType.size()>0)?listNewsType.get(0).getNewsTypeId():-2;
				}
				
				PageHelper.startPage(pageindex,pageSize);
				listNews=newsService.getPCNewsByParam(titleSearch, recommendType, news_Type,0);
				PageInfo<News> page = new PageInfo<News>(listNews);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取司法行政要闻分页列表成功！");
			}
			else
			{		
				if(newsType.equals("-1"))
				{
					String queryType=(listNewsType!=null&&listNewsType.size()>0)?Integer.toString(listNewsType.get(0).getNewsTypeId()):"-1";
					listNews=newsService.getNewsByParam(queryType, token);
				}
				else
				{
					listNews=newsService.getNewsByParam(newsType, token);
				}
							
				jsonObject.put("data", listNews);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取司法行政要闻列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取司法行政要闻列表失败！");
		}
		return jsonObject;
	}
	
	@GetMapping("getLawNewsById")
	@ApiOperation(value="获取相应司法行政要闻id的新闻详情",notes="获取相应司法行政要闻id的新闻详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="司法行政要闻新闻id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getLawNewsById(
			@RequestParam("id") String id)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			News news=newsService.getNewsById(id);
			
			jsonObject.put("data", news);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取司法行政要闻新闻详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取司法行政要闻新闻详情失败！");
		}
		return jsonObject;
	}
	
	@GetMapping("getLawExamList")
	@ApiOperation(value="法考新闻列表",notes="法考新闻列表",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="token",value="设备唯一令牌【仅终端机需要，pc传空字符串】",dataType="String",required=false,paramType="query"),
		@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数,查询条件(搜索条件,推荐状态<0已推荐；-1全部>)】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getLawExamList(
			String token,String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			List<Newstype> listNewsType=newstypeService.getNewsType("3");
			jsonObject.put("types", listNewsType);
			
			List<News> listNews=new ArrayList<News>();
			
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				String titleSearch=pageParams[2];//新闻标题模糊搜索条件
				int recommendType=Integer.parseInt(pageParams[3]);//推荐状态
				
				PageHelper.startPage(pageindex,pageSize);
				listNews=newsService.getPCNewsByParam(titleSearch, recommendType, -3,3);//法考新闻
				PageInfo<News> page = new PageInfo<News>(listNews);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取法考新闻分页列表成功！");
			}
			else
			{		
				listNews=newsService.getLawExamListByParam(-1, token);
							
				jsonObject.put("data", listNews);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取法考新闻列表成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取司法行政要闻列表失败！");
		}
		return jsonObject;
	}
	
	@GetMapping("getExamineeNotice")
	@ApiOperation(value="获取考生须知",notes="获取考生须知",produces="application/json")
	@ApiImplicitParams({
	@ApiImplicitParam(name="pageParams",value="分页相关参数（多个参数用英文逗号分隔）【当前页码,每页条数】",dataType="String",required=false,paramType="query")
	})
	public JSONObject getExamineeNotice(String... pageParams)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			if(pageParams!=null&&pageParams.length>0)
			{
				int pageindex=Integer.parseInt(pageParams[0]);//当前页码
				int pageSize=Integer.parseInt(pageParams[1]);//每页条数
				
				PageHelper.startPage(pageindex,pageSize);		
				List<Examineenotice> list=examineenoticeService.getExamineeNotice();
				PageInfo<Examineenotice> page = new PageInfo<Examineenotice>(list);
				
				jsonObject.put("data", page);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取考生须知分页列表成功！");
			}
			else
			{
				List<Examineenotice> list=examineenoticeService.getExamineeNotice();
				
				jsonObject.put("data", list);
				jsonObject.put("Code", "00000000");
				jsonObject.put("Msg", "获取考生须知成功！");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取考生须知失败！");
		}
		return jsonObject;
	}
	
	@GetMapping("getExamineeNoticeById")
	@ApiOperation(value="获取相应id对应的考生须知详情",notes="获取相应id对应的考生须知详情",produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="考生须知id",dataType="String",required=true,paramType="query")
	})
	public JSONObject getExamineeNoticeById(
			@RequestParam("id") String id)
	{
		JSONObject jsonObject = new JSONObject();

		try 
		{
			Examineenotice examineenotice=examineenoticeService.getExamineeNoticeById(id);
			
			jsonObject.put("data", examineenotice);
			jsonObject.put("Code", "00000000");
			jsonObject.put("Msg", "获取考生须知详情成功！");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			jsonObject.put("Code", "11111111");
			jsonObject.put("Msg", "获取考生须知详情失败！");
		}
		return jsonObject;
	}
}
