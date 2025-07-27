package com.ChallengeBackEnd.ApiRest.service;

import com.ChallengeBackEnd.ApiRest.Autor;
import com.ChallengeBackEnd.ApiRest.Repository.AutorRepository;
import com.ChallengeBackEnd.ApiRest.dto.DatosRegistroAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Autowired
    private AutorRepository autorRepo;
    @Autowired private PasswordEncoder encoder;

    public void registrar(DatosRegistroAutor datos) {
        if (autorRepo.findByEmail(datos.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese email");
        }
        Autor autor = new Autor();
        autor.setNombre(datos.nombre());
        autor.setEmail(datos.email());
        autor.setPassword(encoder.encode(datos.password()));
        autorRepo.save(autor);
    }
}