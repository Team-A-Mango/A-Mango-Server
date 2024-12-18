package com.mango.amango.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HandSign {
    NULL(0),
    CALL(1),
    ROCK(2),
    PEACE(3),
    OK(4);

    private final int value;
}
