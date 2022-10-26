package com.example.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.user.domain.SpUser;

import java.time.Instant;

public class JWTUtil {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("hyunwoo");
    private static final long AUTH_TIME = 20*60;
    private static final long REFRESH_TIME =60*60*24*7;

    public String makeAuthToken(SpUser user) {
        return JWT.create().withSubject(user.getUsername())
                .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
                .sign(ALGORITHM);
    }

    public String makeRefreshToken(SpUser user) {
        return JWT.create().withSubject(user.getUsername())
                .withClaim("exp", Instant.now().getEpochSecond() + REFRESH_TIME)
                .sign(ALGORITHM);
    }

    public static VerfiyResult verfiy(String token) {
        try {
            DecodedJWT verfiy = JWT.require(ALGORITHM).build().verify(token);
            return VerfiyResult.builder().success(true)
                    .username(verfiy.getSubject()).build();
        } catch(Exception e){
            DecodedJWT decoode = JWT.decode(token);
            return VerfiyResult.builder().success(false)
                    .username(decoode.getSubject()).build();
        }
    }
}
