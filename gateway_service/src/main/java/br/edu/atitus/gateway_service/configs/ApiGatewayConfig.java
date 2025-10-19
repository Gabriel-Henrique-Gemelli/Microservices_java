package br.edu.atitus.gateway_service.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

	@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user_service", r -> r.path("/users/**")
						.uri("lb://user-service"))
				.route("order_service", r -> r.path("/orders/**")
						.uri("lb://order-service"))
				.build();
	}
}
