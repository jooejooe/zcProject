package com.xzx.controller;

import com.alibaba.fastjson.JSONObject;
import com.xzx.util.CommonUtil;
import com.xzx.util.TimeExpiredPoolCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/weChat")
@Api(value="微信公众号相关操作")
public class JsSDKController {
    //private static Logger logger = LoggerFactory.getLogger(JsSDKController.class);

    /**
     * 微信公众平台获取AccessToken的访问路径
     */
    @Value("${custom.accessTokenUrl}")
    private String requestUrlAccessToken;
    /**
     * 微信公众平台获取JsapiTicket的访问路径
     */
    @Value("${custom.jsapiTicketUrl}")
    private String requestUrlJsapiTicket;

    /**
     * 微信公众平台获取code
     */
    @Value("${custom.getCodeUrl}")
    private String getCodeUrl;
    /**
     * 微信公众平台根据code获取token和openid
     */
    @Value("${custom.getTokenUrl}")
    private String getTokenUrl;

    @Value("${custom.appid}")
    private String appid;
    @Value("${custom.appsecret}")
    private String appsecret;

    @RequestMapping(value = "/getJsSDK", method = RequestMethod.GET)
    @ApiOperation(value="生成JS-SDK权限验证的签名")
    public JSONObject getJsSDK(@RequestParam("pageUrl") String pageUrl){
        JSONObject jsonObjectres = new JSONObject();
        String accessTokenUrl = "";
        String jsapiTicketUrl = "";
        String access_token = "";
        String jsapi_ticket = "";
        //生成签名的时间戳
        String timestamp = "";
        //生成签名的随机串
        String nonceStr = "";
        //签名
        String signature = "";
        try {
            //从缓存中获取access_token、jsapi_ticket、timestamp、nonceStr、signature
            access_token = TimeExpiredPoolCache.getInstance().get("access_token")==null?null: TimeExpiredPoolCache.getInstance().get("access_token").toString();
            jsapi_ticket = TimeExpiredPoolCache.getInstance().get("jsapi_ticket")==null?null: TimeExpiredPoolCache.getInstance().get("jsapi_ticket").toString();
//            timestamp = TimeExpiredPoolCache.getInstance().get("timestamp")==null?null:TimeExpiredPoolCache.getInstance().get("timestamp").toString();
//            nonceStr = TimeExpiredPoolCache.getInstance().get("nonceStr")==null?null:TimeExpiredPoolCache.getInstance().get("nonceStr").toString();
//            signature = TimeExpiredPoolCache.getInstance().get("signature")==null?null:TimeExpiredPoolCache.getInstance().get("signature").toString();
            System.out.println("从cache取值:access_token-->"+access_token+"    jsapi_ticket-->"+jsapi_ticket+"    timestamp-->"+
                    timestamp+"    nonceStr-->"+nonceStr+"    signature-->"+signature);
        }catch (Exception e){
            e.printStackTrace();
            jsonObjectres.put("Code", "11111111");
            jsonObjectres.put("Msg", "从缓存中获取微信签名相关信息失败！");
        }
        //缓存中access_token、jsapi_ticket、timestamp、nonceStr、signature失效
        if (null==access_token || "".equals(access_token) || null==jsapi_ticket || "".equals(jsapi_ticket)){
            //--------------------------------------------------------1. 获取access_token ----------------------------------------------------
            try {
                accessTokenUrl = requestUrlAccessToken.replace("APPID",appid).replace("APPSECRET",appsecret);
                JSONObject jsonObject = CommonUtil.httpsRequest(accessTokenUrl,"GET",null);
                if(jsonObject.getString("access_token")!=null){
                    access_token = jsonObject.getString("access_token");
                    //缓存20分钟
                    TimeExpiredPoolCache.getInstance().put("access_token",access_token,1000L*20*60);
                    System.out.println("通过api取值:  access_token-->"+access_token);
                    //--------------------------------------------------------2. 获取jsapi_ticket ----------------------------------------------------
                    try {
                        jsapiTicketUrl = requestUrlJsapiTicket.replace("ACCESS_TOKEN",access_token);
                        JSONObject jsonObjectTicket = CommonUtil.httpsRequest(jsapiTicketUrl,"GET",null);
                        if(jsonObjectTicket.getString("ticket")!=null){
                            jsapi_ticket = jsonObjectTicket.getString("ticket");
                            //缓存20分钟
                            TimeExpiredPoolCache.getInstance().put("jsapi_ticket",jsapi_ticket,1000L*20*60);
                            System.out.println("通过api取值:  jsapi_ticket-->"+jsapi_ticket);
                            //--------------------------------------------------------3. 生成JS-SDK权限验证的签名 ----------------------------------------------------
                            try {
                                Map<String, String> ret = CommonUtil.sign(jsapi_ticket, pageUrl);
                                nonceStr = ret.get("nonceStr")==null?"":ret.get("nonceStr");
                                timestamp = ret.get("timestamp")==null?"":ret.get("timestamp");
                                signature = ret.get("signature")==null?"":ret.get("signature");
//                                TimeExpiredPoolCache.getInstance().put("timestamp",timestamp,1000L*20*60);
//                                TimeExpiredPoolCache.getInstance().put("nonceStr",nonceStr,1000L*20*60);
//                                TimeExpiredPoolCache.getInstance().put("signature",signature,1000L*20*60);
                                jsonObjectres.put("timestamp", timestamp);
                                jsonObjectres.put("nonceStr", nonceStr);
                                jsonObjectres.put("signature", signature);
                                System.out.println("通过api取值:  nonceStr-->"+nonceStr);
                                System.out.println("通过api取值:  timestamp-->"+timestamp);
                                System.out.println("通过api取值:  signature-->"+signature);
                            } catch (Exception e){
                                e.printStackTrace();
                                jsonObjectres.put("Code", "11111111");
                                jsonObjectres.put("Msg", "生成签名失败");
                            }
                            jsonObjectres.put("jsapi_ticket", jsapi_ticket);
                        }else{
                            jsonObjectres.put("Code", "11111111");
                            jsonObjectres.put("Msg", "定时刷新jsapi_ticket失败，微信返回的信息是"+jsonObject.toJSONString());
                        }
                    } catch (Exception e){
                        jsonObjectres.put("Code", "11111111");
                        jsonObjectres.put("Msg", "更新jsapi_ticket的过程当中发生了异常，异常的信息是"+e.getMessage());
                    }
                    jsonObjectres.put("access_token", access_token);
                }else{
                    jsonObjectres.put("Code", "11111111");
                    jsonObjectres.put("Msg", "定时刷新access_token失败，微信返回的信息是"+jsonObject.toJSONString());
                }
            } catch (Exception e){
                jsonObjectres.put("Code", "11111111");
                jsonObjectres.put("Msg", "更新access_token的过程当中发生了异常，异常的信息是"+e.getMessage());
            }
            jsonObjectres.put("Code", "00000000");
            jsonObjectres.put("Msg", "成功");
        }else{
            //--------------------------------------------------------3. 生成JS-SDK权限验证的签名 ----------------------------------------------------
            try {
                Map<String, String> rets = CommonUtil.sign(jsapi_ticket, pageUrl);
                nonceStr = rets.get("nonceStr")==null?"":rets.get("nonceStr");
                timestamp = rets.get("timestamp")==null?"":rets.get("timestamp");
                signature = rets.get("signature")==null?"":rets.get("signature");
                jsonObjectres.put("timestamp", timestamp);
                jsonObjectres.put("nonceStr", nonceStr);
                jsonObjectres.put("signature", signature);
                System.out.println("通过api取值:  nonceStr-->"+nonceStr);
                System.out.println("通过api取值:  timestamp-->"+timestamp);
                System.out.println("通过api取值:  signature-->"+signature);
                jsonObjectres.put("access_token", access_token);
                jsonObjectres.put("jsapi_ticket", jsapi_ticket);
                jsonObjectres.put("Code", "00000000");
                jsonObjectres.put("Msg", "成功");
            } catch (Exception e){
                e.printStackTrace();
                jsonObjectres.put("Code", "11111111");
                jsonObjectres.put("Msg", "生成签名失败");
            }
        }
        return jsonObjectres;
    }

    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    @ApiOperation(value="用户同意授权，获取code")
    public void getCode(@RequestParam("redirectUri") String redirectUri, HttpServletResponse response){
        String scope = "snsapi_base";
//        JSONObject jsonObjectres = new JSONObject();
        try {
            String redirect_uri = URLEncoder.encode(redirectUri, "UTF-8");
            System.out.println("redirect_uri--->"+redirect_uri);
            getCodeUrl = getCodeUrl.replace("redirectUri",redirect_uri).replace("APPID",appid).replace("SCOPE",scope);
//            jsonObjectres.put("getCodeUrl", getCodeUrl);
//            jsonObjectres.put("Code", "00000000");
//            jsonObjectres.put("Msg", "成功");
            response.sendRedirect(getCodeUrl);
        } catch (UnsupportedEncodingException e) {
            System.out.println("跳页失败了~~~~~~~");
            e.printStackTrace();
//            jsonObjectres.put("Code", "11111111");
//            jsonObjectres.put("Msg", "从API取值失败");
        } catch (IOException e) {
            System.out.println("跳页失败了~~~~~~~");
            e.printStackTrace();
        }
//        return jsonObjectres;
    }

    @RequestMapping(value = "/getOpenidAndToken", method = RequestMethod.GET)
    @ApiOperation(value="获取code后，获取access_token与openid")
    public JSONObject getOpenidAndToken(@RequestParam("code") String code){
        JSONObject jsonObjectres = new JSONObject();
        String access_token = "";
        String openid="";
        System.out.println("code---->"+code);
        try {
            String newUrl = getTokenUrl.replace("CODE",code).replace("APPID",appid).replace("SECRET",appsecret);
            JSONObject jsonObject = CommonUtil.httpsRequest(newUrl,"GET",null);
            System.out.println("getTokenUrl--->"+newUrl);
            if(jsonObject.getString("access_token")!=null && jsonObject.getString("openid")!=null){
                access_token = jsonObject.getString("access_token");
                openid = jsonObject.getString("openid");
                //缓存20分钟
                TimeExpiredPoolCache.getInstance().put("access_token_code",access_token,1000L*20*60);
                TimeExpiredPoolCache.getInstance().put("openid",openid,1000L*20*60);
                System.out.println("通过api取值:  access_token-->"+access_token);
                System.out.println("通过api取值:  openid-->"+openid);
                jsonObjectres.put("access_token", access_token);
                jsonObjectres.put("openid", openid);
                jsonObjectres.put("Code", "00000000");
                jsonObjectres.put("Msg", "成功");
            }else{
                System.out.println(jsonObject.getString("errcode"));
                System.out.println(jsonObject.getString("errmsg"));
                jsonObjectres.put("Code", "11111111");
                jsonObjectres.put("Msg", "从API取值失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObjectres.put("Code", "11111111");
            jsonObjectres.put("Msg", "从API取值失败");
        }
        return jsonObjectres;
    }
}
