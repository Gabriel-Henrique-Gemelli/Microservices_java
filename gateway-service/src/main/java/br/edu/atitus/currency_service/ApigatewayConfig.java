package br.edu.atitus.currency_service;

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
						.filters(f -> f
								.addRequestHeader("X-USER-NAME", "username")
								.addRequestParameter("name", "fulano"))
						.uri("http:://httpbin.org:80"))
				.route(p -> p
						.path("/products/**")
						.uri("lb://product-service"))
				.route(p -> p
						.path("/currencyt/**")
						.uri("lb://currency-service"))
				.route(p -> p
						.path("/auth/**")
						.uri("lb://auth-service"))
				.build();
				
	}
}
