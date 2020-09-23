package com.xzx.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class TokenUtil {
	//private static final long EXPIRE_TIME = 5*60*1000;//24*60*60*1000;
	
	/**
	 * token私钥
	 */
	private static final String TOKEN_SECRET = "fgdffgsfhfge4e65yhthtuyuhfgh4r556hfdghfhd56hgfhj3545hdf";

	/**
	 * 生成签名,15分钟后过期
	 * @param username
	 * @param userId
	 * @return
	 */
	public static String sign(String username,String userId,String userType,long EXPIRE_TIME){
		//过期时间
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		//私钥及加密算法
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
		//设置头信息
		HashMap<String, Object> header = new HashMap<>(2);
		header.put("typ", "JWT");
		header.put("alg", "HS256");

		//附带username和userID,userType生成签名
		return JWT.create().withHeader(header).withClaim("loginName",username)
				.withClaim("userId",userId).withClaim("userType", userType)
				.withExpiresAt(date).sign(algorithm);
	}


	public static void verity(String token){
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
		JWTVerifier verifier = JWT.require(algorithm).build();
		verifier.verify(token);
	}
}
