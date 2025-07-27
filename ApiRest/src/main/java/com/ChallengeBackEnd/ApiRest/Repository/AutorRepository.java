package com.ChallengeBackEnd.ApiRest.Repository;

import com.ChallengeBackEnd.ApiRest.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByEmail(String email);
}