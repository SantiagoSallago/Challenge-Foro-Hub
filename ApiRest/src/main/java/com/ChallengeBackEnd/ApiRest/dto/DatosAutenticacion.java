package com.ChallengeBackEnd.ApiRest.dto;


import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank String email,
        @NotBlank String password
) {}