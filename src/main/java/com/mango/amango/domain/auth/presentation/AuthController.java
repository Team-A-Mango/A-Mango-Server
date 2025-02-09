package com.mango.amango.domain.auth.presentation;

import com.mango.amango.domain.auth.presentation.dto.request.LoginReq;
import com.mango.amango.domain.auth.presentation.dto.request.SignUpReq;
import com.mango.amango.domain.auth.presentation.dto.response.TokenRes;
import com.mango.amango.domain.auth.service.LogoutService;
import com.mango.amango.domain.auth.service.ReissueTokenService;
import com.mango.amango.domain.auth.service.SignInService;
import com.mango.amango.domain.auth.service.SignUpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignInService signInService;
    private final SignUpService signUpService;
    private final LogoutService logoutService;
    private final ReissueTokenService reissueTokenService;

    @PostMapping
    public ResponseEntity<Void> signUp(
            @Valid @RequestPart("request") SignUpReq request,
            @RequestPart("image") MultipartFile image
    ) {
        signUpService.signUp(request, image);
        return ResponseEntity.status(CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenRes> signIn(@Valid @RequestBody LoginReq request, HttpServletResponse response) {
        TokenRes tokenRes = signInService.signIn(request, response);
        return ResponseEntity.ok(tokenRes);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        logoutService.logout(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TokenRes> reissueToken(HttpServletRequest request) {
        TokenRes tokenRes = reissueTokenService.reissueToken(request);
        return ResponseEntity.ok(tokenRes);
    }
}


