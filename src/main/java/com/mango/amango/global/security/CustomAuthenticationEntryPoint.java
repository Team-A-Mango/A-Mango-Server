package com.mango.amango.global.security;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Primary
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HandlerExceptionResolver resolver;

    public CustomAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        resolver.resolveException(request, response, null, getCustomException(response, authException));
    }

    private CustomException getCustomException(HttpServletResponse response, AuthenticationException authException) {
        if (isSuccessResponse(response) && isAuthError(authException)) {
            return new CustomException(CustomErrorCode.UNAUTHORIZED);

        } else {
            return switch (response.getStatus()) {
                case 401 -> new CustomException(CustomErrorCode.UNAUTHORIZED);
                case 403 -> new CustomException(CustomErrorCode.FORBIDDEN);
                case 404 -> new CustomException(CustomErrorCode.NOT_FOUND);
                case 500 -> new CustomException(CustomErrorCode.INTERNAL_SERVER_ERROR);
                default -> throw authException;

            };
        }
    }

    private boolean isSuccessResponse(HttpServletResponse response) {
        return response.getStatus() == 200 || response.getStatus() == 201;
    }

    private boolean isAuthError(AuthenticationException authException) {
        return authException instanceof InsufficientAuthenticationException;
    }
}
