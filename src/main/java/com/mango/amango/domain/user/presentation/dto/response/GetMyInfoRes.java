package com.mango.amango.domain.user.presentation.dto.response;

import lombok.Builder;

@Builder
public record GetMyInfoRes(
        String nickname,
        String email,
        String phone,
        String profile
) {
}
