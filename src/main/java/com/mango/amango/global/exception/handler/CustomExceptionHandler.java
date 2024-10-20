package com.mango.amango.global.exception.handler;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.exception.CustomErrorRes;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorRes> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        CustomErrorRes response = CustomErrorRes.builder()
                .status(CustomErrorCode.VALIDATION_FAILED)
                .statusMessage(getErrorMessage(e))
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    private String getErrorMessage(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("유효성 검사에 실패했습니다.");
    }
}
