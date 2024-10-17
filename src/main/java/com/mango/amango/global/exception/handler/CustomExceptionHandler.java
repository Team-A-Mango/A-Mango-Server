package com.mango.amango.global.exception.handler;

import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.exception.CustomErrorRes;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorRes> handlerException(
            CustomException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getCustomErrorCode(), request.getRequestURI(), e.getMessage());

        HttpStatus statusCode = e.getCustomErrorCode().getStatusCode();
        CustomErrorRes response = CustomErrorRes.builder()
                .status(e.getCustomErrorCode())
                .statusMessage(e.getDetailMessage())
                .build();

        return ResponseEntity.status(statusCode).body(response);
    }
}
