package com.ChallengeBackEnd.ApiRest.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String clave;

    public String generarToken(UserDetails usuario) {
        return JWT.create()
                .withSubject(usuario.getUsername())
                .withExpiresAt(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant())
                .sign(Algorithm.HMAC256(clave));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(clave))
                .build().verify(token).getSubject();
    }
}