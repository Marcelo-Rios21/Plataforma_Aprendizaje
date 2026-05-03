package com.duoc.LearningPlatformValidation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duoc.LearningPlatformValidation.model.Inscripcion;
import com.duoc.LearningPlatformValidation.repository.InscripcionRepository;

@Service
public class InscripcionService {
    private final InscripcionRepository inscripcionRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> buscarInscripcionPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    public List<Inscripcion> buscarInscripcionesPorCurso(Long cursoId) {
        return inscripcionRepository.findByCursoId(cursoId);
    }

    public Inscripcion registrarInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Optional<Inscripcion> actualizarInscripcion(Long id, Inscripcion inscripcionActualizada) {
        return inscripcionRepository.findById(id).map(inscripcion -> {
            inscripcion.setCursoId(inscripcionActualizada.getCursoId());
            inscripcion.setEstudianteId(inscripcionActualizada.getEstudianteId());
            inscripcion.setFechaInscripcion(inscripcionActualizada.getFechaInscripcion());
            return inscripcionRepository.save(inscripcion);
        });
    }

    public boolean eliminarInscripcion(Long id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
