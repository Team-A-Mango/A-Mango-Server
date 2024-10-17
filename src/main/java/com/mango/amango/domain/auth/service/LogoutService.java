package com.mango.amango.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;

public interface LogoutService {

    void logout(HttpServletRequest request);
}
