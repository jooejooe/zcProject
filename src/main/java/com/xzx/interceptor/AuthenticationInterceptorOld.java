package com.xzx.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xzx.annotation.PassToken;
import com.xzx.annotation.UserLoginToken;
import com.xzx.common.TokenUtil;
import com.xzx.model.Fairworker;
import com.xzx.model.Register;
import com.xzx.service.IFairworkerService;
import com.xzx.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AuthenticationInterceptorOld implements HandlerInterceptor {
	@Autowired
    IRegisterService registerService;
	
	@Autowired
    IFairworkerService fairworkerService;
	
	@Value("${custom.tokenExpiretime}")
	public long EXPIRE_TIME;
	
	//10分钟后刷新token
    //private static final int tokenRefreshInterval = 60 * 10;
	
	@Value("${custom.tokenRefreshInterval}")
	private int tokenRefreshInterval;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
		String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
		//String token = httpServletRequest.getParameter("token");// 从 http 请求query取出 token
		
		// 如果不是映射到方法直接通过
		if(!(object instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod handlerMethod=(HandlerMethod)object;
		Method method=handlerMethod.getMethod();
		
		//检查是否有passtoken注释，有则跳过认证
		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				return true;
			}
		}
		
		//检查有没有需要用户权限的注解
		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) 
			{
				// 执行认证
				if (token == null) 
				{
					httpServletResponse.setContentType("application/json;charset=utf-8");
					
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("Code", "55555555");
					jsonObject.put("Msg", "无token，请重新登录！");
					httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
					return false;
				}
				else
				{
					//验证 token
					try 
					{					
						TokenUtil.verity(token);
						
						// 获取 token 中的 user id
						String userId;
						String userType;
						String userName;
						
						//从参数中的token中获取userId及用户类型
						//userId = JWT.decode(token).getAudience().get(0);
						//userType=JWT.decode(token).getAudience().get(0);
						
						//从header的token中获取userId及用户类型
						userId=JWT.decode(token).getClaim("userId").asString();
						userType=JWT.decode(token).getClaim("userType").asString();
						userName=JWT.decode(token).getClaim("loginName").asString();
						
						//获取用户信息，判断对应用户id对应用户是否存在：不存在返回错误提示，存在续期并返回
						if(userType.equals("0"))//普通用户
						{
							Register register=registerService.getRegisterById(userId);
							
							if(register==null)
							{
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("Code", "66666666");
								jsonObject.put("Msg", "用户不存在，请重新登录！");
								
								httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
								return false;
							}
						}
						else//工作人员
						{
							Fairworker fairworker=fairworkerService.getFairworkById(Integer.parseInt(userId));
							
							if(fairworker==null)
							{
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("Code", "66666666");
								jsonObject.put("Msg", "用户不存在，请重新登录！");
								
								httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
							}
						}
						
						boolean shouldRefresh = shouldTokenRefresh(JWT.decode(token).getExpiresAt());//判断是否需要续期
						
						if(shouldRefresh)
						{
							String newToken= TokenUtil.sign(userName,userId,userType,EXPIRE_TIME);
							httpServletResponse.setHeader("token", newToken);
						}
					}
					catch (TokenExpiredException e) 
					{	
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("Code", "88888888");
						jsonObject.put("Msg", "token过期！");
						
						httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
						return false;
					}
					catch (JWTDecodeException e) {
						
						JSONObject jsonObject = new JSONObject();						
						jsonObject.put("Code", "99999999");
						jsonObject.put("Msg", "token不存在！");
						
						httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
						return false;
					}
					
					return true;
				}
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

	}
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	}
	
    /**
     * 判断是否需要刷新TOKEN(距离到期时间少于tokenRefreshInterval秒钟刷新token)
     * @return 是否需要刷新TOKEN
     */
    private boolean shouldTokenRefresh(Date issueAt) {
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().plusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }
}
