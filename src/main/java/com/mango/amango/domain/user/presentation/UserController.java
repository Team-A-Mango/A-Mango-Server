package com.mango.amango.domain.user.presentation;

import com.mango.amango.domain.user.presentation.dto.response.GetMyInfoRes;
import com.mango.amango.domain.user.presentation.dto.response.GetMyOrdersRes;
import com.mango.amango.domain.user.service.GetMyInfoService;
import com.mango.amango.domain.user.service.GetMyOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class UserController {
    private final GetMyOrdersService getMyOrdersService;
    private final GetMyInfoService getMyInfoService;

    @GetMapping
    public ResponseEntity<GetMyOrdersRes> getMyOrders() {
        GetMyOrdersRes res = getMyOrdersService.execute();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/info")
    public ResponseEntity<GetMyInfoRes> getMyOrdersInfo() {
        GetMyInfoRes res = getMyInfoService.execute();
        return ResponseEntity.ok(res);
    }
}
