package com.xzx.controller;


import com.alibaba.fastjson.JSONObject;
import com.xzx.annotation.UserLoginToken;
import com.xzx.service.SerSystjxx;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
//跨域处理
@CrossOrigin
@Api(value="仲裁业务相关",description="ConSystjxx")
public class ConSystjxx {
    @Resource
    private SerSystjxx serSystjxx;

    @UserLoginToken
    @RequestMapping(value = "/Systj", method = RequestMethod.POST)
    public JSONObject getJson(@RequestBody String json) {
        return serSystjxx.Operation(json);
    }

    @UserLoginToken
    @RequestMapping(value = "/insertZc", method = RequestMethod.POST)
    public JSONObject insertZc(@RequestBody String json){
        return serSystjxx.insertZc(json);
    }

    @UserLoginToken
    @RequestMapping(value = "/findidAuthentic", method = RequestMethod.POST)
    public JSONObject findidAuthentic(@RequestBody String json){
        return serSystjxx.findidAuthentic(json);
    }

//    @UserLoginToken
//    @RequestMapping(value = "/updateAuthentic", method = RequestMethod.POST)
//    public JSONObject updateAuthentic(@RequestBody String json){
//        return serSystjxx.updateAuthentic(json);
//    }


    @UserLoginToken
    @RequestMapping(value = "/getPageSeek", method = RequestMethod.GET)
    public JSONObject getPageSeek(
            @RequestParam("sfzh")  String sfzh,
            @RequestParam("ajlb")  String ajlb,
            @RequestParam("zxjg")  String zxjg,
            @RequestParam("jdlb")  String jdlb,
            @RequestParam("page")  String page,
            @RequestParam("limit") String limit )
    {
        return serSystjxx.getPageSeek(sfzh,ajlb,zxjg,jdlb,limit,page);
    }
    //HttpServletRequest还是好用呀
    @UserLoginToken
    @RequestMapping(value = "/getPageAuthentic", method = RequestMethod.GET)
    public JSONObject getPageAuthentic(HttpServletRequest request) {
        String ajzt = request.getParameter("ajzt");
        String isonline = request.getParameter("isonline");
        String sfzh = request.getParameter("sfzh");
        String ajlb = request.getParameter("ajlb");
        String ksrq = request.getParameter("ksrq");
        String jsrq = request.getParameter("jsrq");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (ajzt==null){
            //System.out.println("null");
            ajzt="";
        }
        return serSystjxx.getPageAuthentic(ajzt,isonline,sfzh,ajlb,ksrq,jsrq,limit,page);
    }

    @UserLoginToken
    @RequestMapping(value = "/getPageAuthenticZc", method = RequestMethod.GET)
    public JSONObject getPageAuthenticZc(HttpServletRequest request) {
        String ajzt = request.getParameter("ajzt");
        String isonline = request.getParameter("isonline");
        String sfzh = request.getParameter("sfzh");
        String ajlb = request.getParameter("ajlb");
        String ksrq = request.getParameter("ksrq");
        String jsrq = request.getParameter("jsrq");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (ajzt==null){
            //System.out.println("null");
            ajzt="";
        }
        return serSystjxx.getPageAuthenticZc(ajzt,isonline,sfzh,ajlb,ksrq,jsrq,limit,page);
    }

    @UserLoginToken
    @RequestMapping(value = "/getPageDZJZ", method = RequestMethod.GET)
    public JSONObject getPageDZJZ(
            @RequestParam("sczt")  String sczt,
            @RequestParam("sfzh")  String sfzh,
            @RequestParam("ajlb")  String ajlb,
            @RequestParam("jzlb")  String jzlb,
            @RequestParam("page")  String page,
            @RequestParam("limit") String limit )
    {
        return serSystjxx.getPageDZJZ(sczt,sfzh,ajlb,jzlb,limit,page);
    }
    @UserLoginToken
    @RequestMapping(value = "/getPageKnowbase", method = RequestMethod.GET)
    public JSONObject getPageKnowbase(
            @RequestParam("title")  String title,
            @RequestParam("state")  String state,
            @RequestParam("page")  String page,
            @RequestParam("limit") String limit )
    {
        return serSystjxx.getPageKnowbase(title,state,limit,page);
    }
    @UserLoginToken
    @RequestMapping(value = "/getPageCasebase", method = RequestMethod.GET)
    public JSONObject getPageCasebase(
            @RequestParam("title")  String title,
            @RequestParam("type")   String type,
            @RequestParam("state")  String state,
            @RequestParam("page")  String page,
            @RequestParam("limit") String limit )
    {
        return serSystjxx.getPageCasebase(title,type,state,limit,page);
    }

}

