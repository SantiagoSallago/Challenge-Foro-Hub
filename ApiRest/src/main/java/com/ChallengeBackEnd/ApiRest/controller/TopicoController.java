package com.ChallengeBackEnd.ApiRest.controller;

import com.ChallengeBackEnd.ApiRest.dto.DatosActualizarTopico;
import com.ChallengeBackEnd.ApiRest.dto.DatosRegistroTopico;
import com.ChallengeBackEnd.ApiRest.dto.DatosRespuestaTopico;
import com.ChallengeBackEnd.ApiRest.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired private TopicoService servicio;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        var topico = servicio.registrar(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @GetMapping
    public List<DatosRespuestaTopico> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public DatosRespuestaTopico detalle(@PathVariable Long id) {
        return servicio.detalle(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public void actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        if (!id.equals(datos.id())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID inconsistente");
        servicio.actualizar(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}