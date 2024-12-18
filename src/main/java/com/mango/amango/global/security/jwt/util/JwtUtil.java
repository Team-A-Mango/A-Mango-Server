package com.mango.amango.global.security.jwt.util;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.security.jwt.TokenStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Service
@Transactional(readOnly = true)
public class JwtUtil {

    public static TokenStatus getTokenStatus(String token, Key secretKey) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return TokenStatus.AUTHENTICATED;
        } catch (ExpiredJwtException | IllegalArgumentException e) {
            return TokenStatus.EXPIRED;
        } catch (JwtException e) {
            throw new CustomException(CustomErrorCode.MALFORMED_TOKEN);
        }
    }

    public static Key getSigningKey(String secretKey) {
        String encodedKey = encodedToBase64(secretKey);
        return Keys.hmacShaKeyFor(encodedKey.getBytes(StandardCharsets.UTF_8));
    }

    public static String toBearerToken(String token) {
        return "Bearer " + token;
    }

    public static String extractBearerToken(String token) {
        if (token == null) {
            return null;
        }

        if (!token.startsWith("Bearer ")) {
            throw new CustomException(CustomErrorCode.MALFORMED_TOKEN);
        }

        return token.substring(7);
    }

    private static String encodedToBase64(String secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
}
