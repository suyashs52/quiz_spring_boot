package com.quiz.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${com.quiz.jwtSecret}")
	private String jwtSecret;

	@Value("${com.quiz.jwtExpiration}")
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {

		com.quiz.security.UserPrinciple userPrincipal = (com.quiz.security.UserPrinciple) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) throws ExpiredJwtException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			LOGGER.error("Invalid JWT signature -> Message: {} ", e);
		} catch (MalformedJwtException e) {
			LOGGER.error("Invalid JWT token -> Message: {}", e);
		}   catch (UnsupportedJwtException e) {
			LOGGER.error("Unsupported JWT token -> Message: {}", e);
		} catch (IllegalArgumentException e) {
			LOGGER.error("JWT claims string is empty -> Message: {}", e);
		}

		return false;
	}

}
