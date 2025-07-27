package com.ChallengeBackEnd.ApiRest.controller;

import com.ChallengeBackEnd.ApiRest.Repository.AutorRepository;
import com.ChallengeBackEnd.ApiRest.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final AutorRepository autorRepo;

    public JwtAuthFilter(TokenService tokenService, AutorRepository autorRepo) {
        this.tokenService = tokenService;
        this.autorRepo = autorRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.replace("Bearer ", "");
            var subject = tokenService.getSubject(token);
            var usuario = autorRepo.findByEmail(subject);
            usuario.ifPresent(user -> {
                var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            });
        }
        chain.doFilter(request, response);
    }
}
