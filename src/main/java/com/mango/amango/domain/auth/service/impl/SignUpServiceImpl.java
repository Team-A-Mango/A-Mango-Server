package com.mango.amango.domain.auth.service.impl;

import com.mango.amango.domain.auth.presentation.dto.request.SignUpReq;
import com.mango.amango.domain.auth.service.SignUpService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.file.s3.S3ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final S3ClientService s3ClientService;

    @Override
    public void signUp(SignUpReq request, MultipartFile image) {
        userService.saveUser(User.builder()
                .email(request.email())
                .nickname(request.nickName())
                .password(passwordEncoder.encode(request.password()))
                .faceImageUrl(s3ClientService.upload(image, ObjectCannedACL.BUCKET_OWNER_READ)
                        .orElseThrow(() -> new CustomException(CustomErrorCode.FILE_PROCESSING_ERROR)))
                .build()
        );
    }
}
