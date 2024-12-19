package com.mango.amango.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한 증명에 실패했습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 페이지 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다."),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "유효하지 않은 비밀번호 입니다."),
    INVALID_EMAIL(HttpStatus.UNAUTHORIZED, "유효하지 않은 이메일 입니다."),
    MALFORMED_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 토큰 형식 입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 유저 입니다."),
    EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "Refresh 토큰을 찾을 수 없습니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "유효하지 않는 요청 형식입니다."),
    NOT_MATCH_USER(HttpStatus.BAD_REQUEST, "해당 자원에 접근할 권한이 없습니다."),

    FILE_CREATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 경로를 찾을 수 없습니다."),
    FILE_PROCESSING_ERROR(HttpStatus.BAD_REQUEST, "처리 할 수 없는 파일입니다."),

    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND,"해당 상품을 찾을 수 없습니다."),
    ALREADY_ORDER_STATUS(HttpStatus.BAD_REQUEST, "이미 처리 된 상품입니다."),

    PRODUCT_ALREADY_TRADED(HttpStatus.BAD_REQUEST, "이미 거래된 상품입니다."),
    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "해당 거래를 찾을 수 없습니다."),
    ORDER_ALREADY_TRADED(HttpStatus.BAD_REQUEST, "이미 거래가 안료되었습니다.")
    ;

    private final HttpStatus statusCode;
    private final String statusMessage;
}
