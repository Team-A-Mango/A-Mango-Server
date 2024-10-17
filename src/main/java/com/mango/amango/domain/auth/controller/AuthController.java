package com.mango.amango.domain.auth.controller;

import com.mango.amango.domain.auth.entity.dto.request.LoginReq;
import com.mango.amango.domain.auth.entity.dto.request.SignUpReq;
import com.mango.amango.domain.auth.entity.dto.response.TokenRes;
import com.mango.amango.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpReq request) {
        authService.signUp(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenRes> login(@Valid @RequestBody LoginReq request, HttpServletResponse response) {
        TokenRes tokenRes = authService.login(request, response);
        return ResponseEntity.ok(tokenRes);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TokenRes> reissueToken(HttpServletRequest request) {
        TokenRes tokenRes = authService.reissueToken(request);
        return ResponseEntity.ok(tokenRes);
    }
}


