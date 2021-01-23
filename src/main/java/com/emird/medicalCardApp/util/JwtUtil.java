package com.emird.medicalCardApp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
	private String SECRET_KEY = "tipitopkey";

	// Generating new token
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
			.setClaims(claims)
			.setSubject(username)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 60000))// Expiration is set to 1 minute
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	// Extract all claims first from payload
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	// Extracting one of the claim/data from token
	// not standalone, used by other extractors
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// We'll use only username and expirationDate extractors
	// Extract only username
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// Extract only expiration date
	public Date extractDate(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	// Token validation
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);

		return (
			username.equals(userDetails.getUsername()) &&
			!isTokenExpired(token)
		);
	}
	// Checking if token is expired
	private boolean isTokenExpired(String token) {
		return extractDate(token).before(new Date());
	}
}
