package swust.yang.util;

//import static org.junit.Assert.*;

import org.junit.Test;

import io.jsonwebtoken.Claims;

public class JwtTokenTest {

	@Test
	public void testCreatToken() {
		String token = JwtToken.creatToken("1", "admin");
		System.out.println("token:" + token);
	}

	@Test
	public void testParseToken() {
		//SignatureException
		String token = JwtToken.creatToken("1", "admin");
		try {
			Claims claims = JwtToken.parseToken(token);
			System.out.println(claims.getId());
			System.out.println(claims.get("uid", String.class));
			System.out.println(claims.get("role", String.class));
		} catch(Exception e) {
			System.out.println("token error!");
		}
		
	}

}
