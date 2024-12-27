package com.mango.amango.domain.order.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.util.OrderConverter;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class OrderClient {

    @Value("${ai-server.base-url}")
    private String baseUrl;
    private final UserService userService;
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    private WebClient getWebClient() {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        return webClientBuilder
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                })
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void postOrderIdentity(Order order) {
        User user = userService.getCurrentUser();

        getWebClient().post()
                .uri("/api/a-mango/get_parameters")
                .bodyValue(OrderConverter.toDto(user, order))
                .retrieve()
                .toBodilessEntity()
                .timeout(Duration.ofSeconds(5))
                .onErrorMap((e) -> new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR))
                .block()
        ;
    }
}
