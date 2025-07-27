package com.ChallengeBackEnd.ApiRest.service;

import com.ChallengeBackEnd.ApiRest.Autor;
import com.ChallengeBackEnd.ApiRest.Curso;
import com.ChallengeBackEnd.ApiRest.Repository.AutorRepository;
import com.ChallengeBackEnd.ApiRest.Repository.CursoRepository;
import com.ChallengeBackEnd.ApiRest.Repository.TopicoRepository;
import com.ChallengeBackEnd.ApiRest.Topico;
import com.ChallengeBackEnd.ApiRest.dto.DatosActualizarTopico;
import com.ChallengeBackEnd.ApiRest.dto.DatosRegistroTopico;
import com.ChallengeBackEnd.ApiRest.dto.DatosRespuestaTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepo;
    @Autowired private AutorRepository autorRepo;
    @Autowired private CursoRepository cursoRepo;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datos) {
        if (topicoRepo.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tópico duplicado");
        }

        Autor autor = autorRepo.findById(datos.autorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado"));
        Curso curso = cursoRepo.findById(datos.cursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepo.save(topico);
        return new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), autor.getNombre(), curso.getNombre());
    }

    public List<DatosRespuestaTopico> listar() {
        return topicoRepo.findAll().stream()
                .map(t -> new DatosRespuestaTopico(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.getStatus(), t.getAutor().getNombre(), t.getCurso().getNombre()))
                .toList();
    }

    public DatosRespuestaTopico detalle(Long id) {
        Topico t = topicoRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        return new DatosRespuestaTopico(t.getId(), t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.getStatus(), t.getAutor().getNombre(), t.getCurso().getNombre());
    }

    public void eliminar(Long id) {
        if (!topicoRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }
        topicoRepo.deleteById(id);
    }

    public void actualizar(DatosActualizarTopico datos) {
        Topico topico = topicoRepo.findById(datos.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
    }
}
