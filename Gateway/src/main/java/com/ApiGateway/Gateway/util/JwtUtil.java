package com.ApiGateway.Gateway.util;

import java.security.Key;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	public static final String SECRET = "7B6BCC9C7223548A33C2A565D2BE2189E1F743D2745CF011E7F09B9F255FF963";


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }


    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
    
    public String extractUsername(String token) {
    	return extractClaim(token, Claims::getSubject);
    }


	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}


	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder().
				setSigningKey(getSignKey()).build()
				.parseClaimsJws(token)
				.getBody();
	}
    
}
