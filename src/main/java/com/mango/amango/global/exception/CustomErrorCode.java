package com.mango.amango.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "유효하지 않은 비밀번호 입니다."),
    INVALID_EMAIL(HttpStatus.UNAUTHORIZED, "유효하지 않은 이메일 입니다."),
    MALFORMED_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 토큰 형식 입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 유저 입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "Refresh 토큰을 찾을 수 없습니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "유효하지 않는 요청 형식입니다."),
    FILE_CREATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 경로를 찾을 수 없습니다."),
    FILE_PROCESSING_ERROR(HttpStatus.BAD_REQUEST, "처리 할 수 없는 파일입니다."),
    MAIL_DELIVERY_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "매일 전송을 실패하였습니다."),

    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND,"해당 상품을 찾을 수 없습니다.")
    ;

    private final HttpStatus statusCode;
    private final String statusMessage;
}
