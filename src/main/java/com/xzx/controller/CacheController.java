package com.xzx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xzx.util.TimeExpiredPoolCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/userIdCache")
@Api(value="cache缓存存入和获取")
public class CacheController {

    @RequestMapping(value = "/setUserIdCache", method = RequestMethod.GET)
    @ApiOperation(value="存入缓存（30min）")
    @SuppressWarnings("unchecked")
    public JSONObject setUserIdCache(@RequestParam("userId") String userId, @RequestParam("anjianId") String anjianId, @RequestParam("roomId") String roomId,
                                     @RequestParam("roompwd") String roompwd, @RequestParam("realname") String realname, @RequestParam("workerId") String workerId,
                                     @RequestParam("workername") String workername, @RequestParam("usertype") String usertype){
        JSONObject jsonObject = new JSONObject();
        try {
            String token = TimeExpiredPoolCache.getInstance().get(userId)==null?null:TimeExpiredPoolCache.getInstance().get(userId).toString();
            List<Map<String,Object>> listObjectSec = new ArrayList<>();
            if(token != null && !"".equals(token)){
                listObjectSec = JSONArray.parseObject(token,List.class);
            }
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String setTime = sdf.format(now);
            Map<String, Object> mapres = new HashMap<>();
            mapres.put("userId",userId);
            mapres.put("anjianId",anjianId);
            mapres.put("roomId",roomId);
            mapres.put("roompwd",roompwd);
            mapres.put("realname",realname);
            mapres.put("workerId",workerId);
            mapres.put("workername",workername);
            mapres.put("usertype",usertype);
            mapres.put("setTime",setTime);
            listObjectSec.add(0,mapres);
            String jsonString = JSONArray.parseArray(JSON.toJSONString(listObjectSec)).toJSONString();
            //缓存30分钟
            TimeExpiredPoolCache.getInstance().put(userId,jsonString,1000L*30*60);
            jsonObject.put("Code", "00000000");
            jsonObject.put("Msg", "缓存成功！");
        } catch (Exception e) {
            jsonObject.put("Code", "11111111");
            jsonObject.put("Msg", "缓存失败！");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getUserIdCache", method = RequestMethod.GET)
    @ApiOperation(value="从缓存中取出，参数为userId")
    public JSONObject  getUserIdCache(@RequestParam("userId") String userId){
        JSONObject jsonObject = new JSONObject();
        try {

            String token = TimeExpiredPoolCache.getInstance().get(userId)==null?null:TimeExpiredPoolCache.getInstance().get(userId).toString();
            if (null!=token){
                JSONArray jsonArray= JSONArray.parseArray(token);
                jsonObject.put("Data", jsonArray);
                jsonObject.put("Code", "00000000");
                jsonObject.put("Msg", "获取成功！");
            }else{
                jsonObject.put("Code", "11111111");
                jsonObject.put("Msg", "该用户信息未在缓存中！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("Code", "11111111");
            jsonObject.put("Msg", "获取失败！");
        }
        return jsonObject;
    }

}
