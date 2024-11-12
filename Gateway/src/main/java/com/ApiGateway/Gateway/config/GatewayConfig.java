package com.ApiGateway.Gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.ApiGateway.Gateway.filter.AuthenticationFilter;

@Configuration
public class GatewayConfig {

//	@Autowired
//	private AuthenticationFilter authenticationFilter;
	
	@Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
	
//	@Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//            .route("test_user_info", r -> r.path("/api/gateway/v1/user-info")  // The specific path to route
//            	.filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())).preserveHostHeader())
//                .uri("forward:/")
//            )
//            .build();
//    }
	
	
	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthenticationFilter authFilter) {
        return builder.routes()
                .route("User-Data-Management", r -> r
                        .path("/api/user/v1/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://User-Data-Management"))
                .route("Property-Data-Management", r -> r
                        .path("/api/property/v1/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Property-Data-Management"))
                .route("Tax-Data-Management", r -> r
                        .path("/api/tax/v1/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Tax-Data-Management"))
                .route("Gateway", r -> r
                        .path("/api/gateway/v1/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8084"))
                .route("Auth-Authentication-Manager", r -> r
                        .path("/api/auth/v1/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://Auth-Authentication-Manager"))
                .build();
    }
}
