package swust.yang.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {
	
	// 秘钥
	private static final String SECRET = "swustyang";
	
	public static String creatToken(String uid,String role) {
		
		// 签发时间
		Date iatDate = new Date();
		
		// 过期时间，7天
		Calendar expireTime = Calendar.getInstance();
		expireTime.add(Calendar.MINUTE, 30);
		
		//header，可自动创建
		Map<String,Object> header = new HashMap<String,Object>();
		header.put("alg", "HS256");
		header.put("typ", "JWT");
		
		//payload的私有声明
		Map<String,Object> claims = new HashMap<String,Object>();
		claims.put("uid", uid);
		claims.put("role", role);
		
		//生成token
		JwtBuilder builder = Jwts.builder()
							     .setHeaderParams(header)
							     .setClaims(claims)
							     .setId(uid)
							     .setIssuedAt(iatDate)
							     .setExpiration(expireTime.getTime())
							     .signWith(SignatureAlgorithm.HS256, SECRET);
		String token = builder.compact();
		return token;
	}
	
	public static Claims parseToken(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(SECRET)
							.parseClaimsJws(token)
							.getBody();
		return claims;
	}
}
