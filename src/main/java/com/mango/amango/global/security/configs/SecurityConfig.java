package com.mango.amango.global.security.configs;

import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.filter.CustomExceptionFilter;
import com.mango.amango.global.security.jwt.filter.JwtAuthenticationFilter;
import com.mango.amango.global.security.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import static com.mango.amango.domain.user.entity.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] PERMITTED_URI = {"/auth", "/auth/login"};
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(DELETE, "/auth").authenticated()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(POST, "/email").permitAll()

                        .requestMatchers(GET, "/product/**").permitAll()
                        .requestMatchers(POST, "/product").hasAuthority(USER.getKey())
                        .requestMatchers(POST, "/product/**").hasAuthority(USER.getKey())
                        .requestMatchers(PATCH, "/product/**").hasAuthority(USER.getKey())

                        .requestMatchers(POST, "/inquiry/**").hasAuthority(USER.getKey())

                        .requestMatchers(GET, "/my/**").authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userService),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CustomExceptionFilter(),
                        JwtAuthenticationFilter.class)
                .exceptionHandling((exception) -> exception.authenticationEntryPoint(authenticationEntryPoint))
        ;

        return http.build();
    }
}
