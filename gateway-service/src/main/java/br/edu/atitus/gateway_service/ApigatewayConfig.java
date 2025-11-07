package br.edu.atitus.gateway_service;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApigatewayConfig {

    @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(p -> p
                .path("/get")
                .filters(f -> f.addRequestHeader("X-USER-NAME", "username").addRequestParameter("name", "fulano"))
                .uri("http://httpbin.org:80"))
            .route(p -> p
                .path("/product/**")
                .filters(f -> f.rewritePath("/product/(?<segment>.*)", "/${segment}"))
                .uri("lb://product-service"))
            .route(p -> p
                .path("/currency/**")
                .filters(f -> f.rewritePath("/currency/(?<segment>.*)", "/${segment}"))
                .uri("lb://currency-service"))
            .route(p -> p
                .path("/auth/**")
                .filters(f -> f.rewritePath("/auth/(?<segment>.*)", "/${segment}"))
                .uri("lb://auth-service"))
            .route(p -> p
                .path("/orders/**")
                .filters(f -> f.rewritePath("/orders/(?<segment>.*)", "/${segment}"))
                .uri("lb://order-service"))
            .route(p -> p
                .path("/ws/orders/**")
                .filters(f -> f.rewritePath("/ws/orders/(?<segment>.*)", "/${segment}"))
                .uri("lb:ws://order-service"))
            .route(p -> p
                .path("/greeting/**")
                .filters(f -> f.rewritePath("/greeting/(?<segment>.*)", "/${segment}"))
                .uri("lb://greeting-service"))
            .route(p -> p
                .path("/cart/**")
                .filters(f -> f.rewritePath("/cart/(?<segment>.*)", "/${segment}"))
                .uri("lb://cart-service"))
            .build();
    }
}
