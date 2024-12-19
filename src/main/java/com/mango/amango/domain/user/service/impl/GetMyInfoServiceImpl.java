package com.mango.amango.domain.user.service.impl;

import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.presentation.dto.response.GetMyInfoRes;
import com.mango.amango.domain.user.service.GetMyInfoService;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.domain.user.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetMyInfoServiceImpl implements GetMyInfoService {

    private final UserService userService;

    public GetMyInfoRes execute() {
        User user = userService.getCurrentUser();

        return UserConverter.toUserDto(user);
    }
}
