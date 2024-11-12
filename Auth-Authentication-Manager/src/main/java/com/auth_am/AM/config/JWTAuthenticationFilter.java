package com.auth_am.AM.config;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	@Autowired
	private JwtUtil jwtUtil;

//	private final JwtUtil jwtUtil;
//
//    @Autowired
//    public JWTAuthenticationFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Check if Authorization header exists and starts with Bearer
		logger.info("Reached Bearer");
		logger.info("Token: {}", authHeader);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extract the token
            logger.info("Token Value Now: {}", token);
            
            
            try {
	                // Validate token and extract user details
	            	jwtUtil.validateToken(token);
	            	logger.info("Reached Here!!");
            	
            	
                    String username = jwtUtil.extractUsername(token);
                    // Populate the SecurityContextHolder with authentication details
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                
            } catch (Exception e) {
                logger.error("Token validation failed", e);
            }
        }
      filterChain.doFilter(request, response);
    }
}
