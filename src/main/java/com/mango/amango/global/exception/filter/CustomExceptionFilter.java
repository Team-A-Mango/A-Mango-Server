package com.mango.amango.global.exception.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.exception.CustomErrorRes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            setErrorResponse(response, e);
        }
    }

    private void setErrorResponse(HttpServletResponse response, CustomException customException) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(customException.getCustomErrorCode().getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        CustomErrorRes customErrorRes = CustomErrorRes.builder()
                .status(customException.getCustomErrorCode())
                .statusMessage(customException.getDetailMessage())
                .build();
        try {
            response.getWriter().write(objectMapper.writeValueAsString(customErrorRes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
