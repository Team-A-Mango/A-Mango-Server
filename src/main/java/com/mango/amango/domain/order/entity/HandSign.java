package com.mango.amango.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HandSign {
    CALL("Call"),
    ROCK("rock"),
    PEACE("peace"),
    OK("Ok");

    private final String value;
}
