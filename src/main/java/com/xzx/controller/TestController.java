package com.xzx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzx.model.Organization;
import com.xzx.model.Student;
import com.xzx.service.IStudentService;
import com.xzx.service.OrganizationService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("custom")
public class TestController {
	
    @Autowired
    private OrganizationService organizationService;
    
    @Autowired
    private IStudentService studentService;

    @PostMapping("getOrgListByParam")
	public JSONObject getOrgListByParam(@RequestBody String request){
		JSONObject requestObject = JSONObject.parseObject(request);

		int id = Integer.parseInt(requestObject.getString("id"));//用户id
		
		String name=requestObject.getString("name");

		List<Organization> list=organizationService.getOrgListByParam(Integer.toString(id), name);
		
		JSONObject organizationResponse = new JSONObject();
		
		organizationResponse.put("data", list);

		return organizationResponse;
	}
    
	@GetMapping("getOrganizationById")
	public JSONObject getOrganizationById(String id) throws Exception {
		Organization organization=organizationService.getOrgById(id);
		
		JSONObject organizationResponse = new JSONObject();
		
		organizationResponse.put("data", organization);

		return organizationResponse;
	}
	
	//分页
	@GetMapping("getOrgPageList")
	public PageInfo<Organization> getOrgPageList(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize)
	{
		PageHelper.startPage(pageNo,pageSize);
		
		List<Organization> list=organizationService.getOrgListByParam("", "");
		
		PageInfo<Organization> page = new PageInfo<Organization>(list);
		
		return page;
	}
	
    @PostMapping("getStuList")
	public JSONObject getStuList(){
		List<Student> list=studentService.getStuList();
		
		JSONObject stuResponse = new JSONObject();
		
		stuResponse.put("data", list);

		return stuResponse;
	}
}