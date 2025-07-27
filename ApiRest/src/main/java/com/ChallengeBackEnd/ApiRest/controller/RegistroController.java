package com.ChallengeBackEnd.ApiRest.controller;

import com.ChallengeBackEnd.ApiRest.dto.DatosRegistroAutor;
import com.ChallengeBackEnd.ApiRest.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegistroController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> registrar(@RequestBody @Valid DatosRegistroAutor datos) {
        authService.registrar(datos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
