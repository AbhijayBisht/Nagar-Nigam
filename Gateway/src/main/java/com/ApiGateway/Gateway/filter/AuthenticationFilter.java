package com.ApiGateway.Gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ApiGateway.Gateway.externalService.Auth_Manager_Service;
import com.ApiGateway.Gateway.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private final Auth_Manager_Service authManagerFeignClient;
	
	public AuthenticationFilter(@Lazy Auth_Manager_Service authManagerFeignClient) {
        super(Config.class);
        this.authManagerFeignClient = authManagerFeignClient;
    }

	@PostConstruct
	public void init() {
	    logger.info("!!!!!!!!!!!!!!!!!!!!AuthenticationFilter initialized and registered!!!!!!!!!!!!!!!!!!!!");
	}
	
	public static class Config {}
	
//	@Autowired
//  private Auth_Manager_Service authManagerFeignClient;
//    @Lazy
//    @Autowired
//    public void setAuthManagerFeignClient(Auth_Manager_Service authManagerFeignClient) {
//    	this.authManagerFeignClient = authManagerFeignClient;
//    }

	
 
	

	@Override
	public GatewayFilter apply(Config config) {
		
		return ((exchange, chain)->{
			logger.debug("\nEnter Authentication Filter");
			
			ServerHttpRequest httpRequest = null;
					
				logger.debug("\n--- Authentication Filter Debug Information ---");
	            logger.debug("Request URL: {}", exchange.getRequest().getURI());
	            logger.debug("Request Headers: {}", exchange.getRequest().getHeaders());
	            logger.info("Processing request: {} {}", exchange.getRequest().getMethod(),exchange.getRequest().getURI().getPath());
	            
	            
	            		logger.debug("\n{}",routeValidator.isSecured.test(exchange.getRequest()));
						if (routeValidator.isSecured.test(exchange.getRequest())) {
//							System.out.println("routeValidator: "+routeValidator);
				                //header contains token or not
				                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				                	logger.error("Missing or invalid authorization header");
				                    throw new RuntimeException("Missing authorization header");
				                }
				                
				                
				                
				                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);		//it's a token
				                System.out.println("Token: "+authHeader);
				                logger.error("Token: {}",authHeader);
				                
				                if (authHeader != null && authHeader.startsWith("Bearer ")) {
				                    authHeader = authHeader.substring(7);
				                }
				                try {
				                	System.out.println("Checking if it even reach here?");
				                    jwtUtil.validateToken(authHeader);
				                    logger.debug("Auth Header: {}", authHeader);
				                    System.out.println("authHeader: "+authHeader);
				                    
				                    // getting username from the token
				                    String username = jwtUtil.extractUsername(authHeader);
				                    System.out.println("username: "+username);
				                    // Call auth-manager-service via feign-client to get roles for that user
				                    List<String> roles = authManagerFeignClient.getRolesByUsername(username);
				                    System.out.println("roles: "+roles);
				                    
				                    String rolesAsString = String.join(",", roles);
				                    System.out.println("roles: "+rolesAsString);
				                    
				                    
				                    logger.debug("Authenticated User: {}", username);
				                    logger.debug("User Roles: {}", rolesAsString);
// 			Convert roles to authorities									||	API-Gateway-services, where you don't need detailed access control 
//			List<SimpleGrantedAuthority> authorities = roles.stream()		||	(and the gateway is mainly forwarding requests), you don't need to 
//								 .map(SimpleGrantedAuthority::new)			||	convert roles to authorities unless you're directly using Spring Security
//				                 .collect(Collectors.toList());				||	features in the gateway
// 		You can now use the authorities for further processing if needed	||

				                    
				                    // adding the roles to headers for other services to perform specific functions:
				                    httpRequest = exchange.getRequest().mutate()
				                    		 .header("loggedInUser", jwtUtil.extractUsername(authHeader))
				                    		 .header("loggedInUserRoles", rolesAsString) 		// Only for now as we are taking roles as single value and not as list
				                             .build();
				                    logger.debug("Header Info: {}", httpRequest);
				                    		
				                } catch (ExpiredJwtException e) {
				                    logger.error("JWT token has expired", e);
				                    throw new RuntimeException("JWT token has expired", e);
				                } catch (MalformedJwtException e) {
				                    logger.error("JWT token is malformed", e);
				                    throw new RuntimeException("JWT token is malformed", e);
				                } catch (SignatureException e) {
				                    logger.error("JWT signature is invalid", e);
				                    throw new RuntimeException("JWT signature is invalid", e);
				                } catch (Exception e) {
				                	logger.error("Invalid access: Unauthorized access to application", e);
				                    System.out.println("Invalid access...!");
				                    throw new RuntimeException("!!Unauthorized access to application!!");
				                }
			            }
			
			logger.debug("---------------------------\n");
			return chain.filter(exchange.mutate().request(httpRequest).build());
		
		});
	}
}	