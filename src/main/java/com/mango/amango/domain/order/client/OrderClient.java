package com.mango.amango.domain.order.client;

import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.util.OrderConverter;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class OrderClient {

    @Value("${ai-server.base-url}")
    private String baseUrl;
    private final UserService userService;
    private final WebClient.Builder webClientBuilder;

    private WebClient getWebClient() {
        return webClientBuilder
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void postOrderIdentity(Order order) {
        User user = userService.getCurrentUser();

        getWebClient().post()
                .uri("/api/a-mango/face_recognition")
                .bodyValue(OrderConverter.toDto(user, order))
                .retrieve()
                .toBodilessEntity()
                .block()
        ;
    }
}
