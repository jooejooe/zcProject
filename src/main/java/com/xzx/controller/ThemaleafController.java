package com.xzx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzx.model.Organization;
import com.xzx.service.OrganizationService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("themaleafCon")
public class ThemaleafController {
    @Autowired
    private OrganizationService organizationService;
    
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("msg","Spring boot集成Thymeleaf");
        return "index"; //返回的是一个页面，可以省略后缀.html
    }
    
    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","测试Thymeleaf");
        return "test"; //返回的是一个页面，可以省略后缀.html
    }
    
    @GetMapping("/organization/orgList")
    public String orgList(Model model){
    	List<Organization> listOrg=organizationService.getOrgListByParam("", "");
        model.addAttribute("orgList",listOrg);
        return "organization/orgList"; //返回的是一个页面，可以省略后缀.html
    }
    
    @GetMapping("/submitForm")
    public String submitForm(Model model){
        model.addAttribute("msg","测试表单提交");
        return "submitForm"; //返回的是一个页面，可以省略后缀.html
    }
}
