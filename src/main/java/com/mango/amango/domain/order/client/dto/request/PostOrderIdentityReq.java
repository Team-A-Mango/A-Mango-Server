package com.mango.amango.domain.order.client.dto.request;

import lombok.Builder;

@Builder
public record PostOrderIdentityReq(
        String image_url,
        String hand_sign,
        Integer storage_number
) {
}
