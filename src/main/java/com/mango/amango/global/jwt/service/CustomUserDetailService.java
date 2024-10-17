package com.mango.amango.global.jwt.service;

import com.mango.amango.domain.user.repository.UserRepository;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.jwt.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findById(UUID.fromString(userId))
                .map(UserPrincipal::new)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
    }
}
