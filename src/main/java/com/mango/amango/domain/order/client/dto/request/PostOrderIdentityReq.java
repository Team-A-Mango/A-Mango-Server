package com.mango.amango.domain.order.client.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PostOrderIdentityReq(
        String imageUrl,
        String handSign,
        String storageRoomNumber) {
}
