package com.ApiGateway.Gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	private static final Logger logger = LoggerFactory.getLogger(RouteValidator.class);
	
	public static final List<String> openApiEndpoints = List.of(
            "/api/auth/v1/register",
            "/api/auth/v1/token",
            "/eureka",
            "/api-docs"
    );
	
//	public Predicate<ServerHttpRequest> isSecured =
//            request -> openApiEndpoints
//                    .stream()
//                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
            
    public Predicate<ServerHttpRequest> isSecured = request -> {
                String currentPath = request.getURI().getPath();
                logger.info("Checking security for path: {}", currentPath);
                
                boolean isOpen = openApiEndpoints.stream()
                    .anyMatch(uri -> currentPath.contains(uri));  
                
                logger.info("Path {} is {}secured", currentPath, isOpen ? "not " : "");
                return !isOpen;
            };        
}
