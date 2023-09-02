package com.foodchoice.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {
	
	private SecretKey key=Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
	
	
	public String getEmailFromJwtToken(String jwt) {
		
		System.out.println("Inside Token provider");
		
		Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		System.out.println("After claims");
		String email=String.valueOf(claims.get("username"));
		
		return email;
	}
	

}
