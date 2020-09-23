package com.xzx.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.xzx.annotation.PassToken;
import com.xzx.annotation.UserLoginToken;
import com.xzx.common.RedisUtil;
import com.xzx.model.Fairworker;
import com.xzx.model.Register;
import com.xzx.service.IFairworkerService;
import com.xzx.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationInterceptor implements HandlerInterceptor {
	@Autowired
    IRegisterService registerService;

	@Autowired
    IFairworkerService fairworkerService;

	@Value("${custom.tokenExpiretime}")
	public long EXPIRE_TIME;

	@Resource
	RedisUtil redisUtil;

	//10分钟后刷新token
	//private static final int tokenRefreshInterval = 60 * 10;

	@Value("${custom.tokenRefreshInterval}")
	private int tokenRefreshInterval;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
		String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token

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
					jsonObject.put("Code", "66666666");
					jsonObject.put("Msg", "无token，请重新登录！");
					httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
					return false;
				}
				else
				{				
						
					// 获取 token 中的 user id
					String userId = null;
					String userType = null;

					try
					{
						//从header的token中获取userId及用户类型
						userId=JWT.decode(token).getClaim("userId").asString();
						userType=JWT.decode(token).getClaim("userType").asString();
					}
					catch(JWTDecodeException e)
					{
						JSONObject jsonObject = new JSONObject();						
						jsonObject.put("Code", "99999999");
						jsonObject.put("Msg", "token不存在！");
						
						httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
						return false;
					}

					String redisKey=userId+","+userType;
					
					if(redisUtil.isHaveKey(redisKey))
					{
						if(redisUtil.getValue(redisKey).equals(token))
						{
							//获取用户信息，判断对应用户id对应用户是否存在：不存在返回错误提示，存在续期并返回
							if(userType.equals("0"))//普通用户
							{
								Register register=registerService.getRegisterById(userId);

								if(register==null)
								{
									JSONObject jsonObject = new JSONObject();
									jsonObject.put("Code", "77777777");
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
									jsonObject.put("Code", "77777777");
									jsonObject.put("Msg", "用户不存在，请重新登录！");

									httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
								}
							}

							Map<String, Object> map=new HashMap<String, Object>();
							map.put("key", userId+","+userType);
							map.put("value", userId);
							redisUtil.add(map);
						}
						else
						{
							JSONObject jsonObject = new JSONObject();						
							jsonObject.put("Code", "99999999");
							jsonObject.put("Msg", "token不存在！");

							httpServletResponse.getWriter().write(JSON.toJSONString(jsonObject));
							return false;
						}
					}
					else
					{
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("Code", "88888888");
						jsonObject.put("Msg", "token过期！");

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
}
