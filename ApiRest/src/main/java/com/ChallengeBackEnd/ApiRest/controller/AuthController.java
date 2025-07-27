package com.ChallengeBackEnd.ApiRest.controller;

import com.ChallengeBackEnd.ApiRest.dto.DatosAutenticacion;
import com.ChallengeBackEnd.ApiRest.dto.DatosJWTToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.ChallengeBackEnd.ApiRest.service.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> login(@RequestBody @Valid DatosAutenticacion datos) {
        var authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var auth = authManager.authenticate(authToken);
        var jwt = tokenService.generarToken((UserDetails) auth.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwt));
    }
}
