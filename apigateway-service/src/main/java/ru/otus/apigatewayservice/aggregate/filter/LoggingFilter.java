package ru.otus.apigatewayservice.aggregate.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: URUNOV Khamdamboy
 * @date 29.06.2026
 * @Project: apigateway-service
 * @description Global filter that logs method, URI and response status for every routed request
 */
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        LOG.info("GATEWAY REQUEST >> method={} uri={}", request.getMethod(), request.getURI());

        return chain.filter(exchange).then(Mono.fromRunnable(() ->
                LOG.info("GATEWAY RESPONSE >> status={}", exchange.getResponse().getStatusCode())
        ));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
