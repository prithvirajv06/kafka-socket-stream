package com.prithvi.gateway.config;

import com.prithvi.gateway.filter.SubscriptionKeyFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private final SubscriptionKeyFilter filter;

    public GatewayConfig(SubscriptionKeyFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("stockService", r -> r.path("/v1/manage/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://STOCK-SERVICE"))

                .route("userServiceWS", r -> r.path("/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb:ws://USERSERVICE"))

                .route("userServiceWS", r -> r.path("/kafka-websocket/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb:ws://USERSERVICE"))
                // Add other routes

                .build();
    }
}