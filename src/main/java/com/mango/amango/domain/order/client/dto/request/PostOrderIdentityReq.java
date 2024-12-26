package com.mango.amango.domain.order.client.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class PostOrderIdentityReq {
    private final String image_url;
    private final String hand_sign;
}
