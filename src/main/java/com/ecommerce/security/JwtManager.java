package com.ecommerce.security;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.constant.SecurityConstant;
import com.ecommerce.domain.Usuario;
import com.ecommerce.dto.UserLoginResponsedDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {

	public UserLoginResponsedDto createdToken(String email, List<String> roles, Usuario username) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, SecurityConstant.JWT_EXP_DAYS);
		String jwt = Jwts.builder().setSubject(email)
				.setExpiration(calendar.getTime())
				.claim(SecurityConstant.JWT_ROLE, roles)
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.API_KEY.getBytes())
				.compact();
		Long expireIn = calendar.getTimeInMillis();
		return new UserLoginResponsedDto(jwt, expireIn, SecurityConstant.JWT_PROVIDER, username);
	}
	
	public Claims parseToken(String jwt) throws JwtException {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstant.API_KEY.getBytes())
				.parseClaimsJws(jwt)
				.getBody();
		
		return claims;
	}
}
