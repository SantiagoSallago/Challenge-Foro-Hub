package com.ChallengeBackEnd.ApiRest.Repository;

import com.ChallengeBackEnd.ApiRest.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {}