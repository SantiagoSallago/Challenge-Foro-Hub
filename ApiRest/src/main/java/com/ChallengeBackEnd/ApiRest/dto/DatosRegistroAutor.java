package com.ChallengeBackEnd.ApiRest.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroAutor(
        @NotBlank String nombre,
        @NotBlank String email,
        @NotBlank String password
) {}