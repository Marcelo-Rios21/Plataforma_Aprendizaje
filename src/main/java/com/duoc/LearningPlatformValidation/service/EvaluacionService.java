package com.duoc.LearningPlatformValidation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duoc.LearningPlatformValidation.model.Evaluacion;
import com.duoc.LearningPlatformValidation.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Optional<Evaluacion> buscarEvaluacionPorId(Long id) {
        return evaluacionRepository.findById(id);
    }

    public List<Evaluacion> buscarEvaluacionesPorCurso(Long cursoId) {
        return evaluacionRepository.findByCursoId(cursoId);
    }

    public Evaluacion registrarEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Optional<Evaluacion> actualizarEvaluacion(Long id, Evaluacion evaluacionActualizada) {
        return evaluacionRepository.findById(id).map(evaluacion -> {
            evaluacion.setCursoId(evaluacionActualizada.getCursoId());
            evaluacion.setNombre(evaluacionActualizada.getNombre());
            evaluacion.setPuntajeMaximo(evaluacionActualizada.getPuntajeMaximo());
            evaluacion.setFechaAplicacion(evaluacionActualizada.getFechaAplicacion());
            return evaluacionRepository.save(evaluacion);
        });
    }

    public boolean eliminarEvaluacion(Long id) {
        if (evaluacionRepository.existsById(id)) {
            evaluacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
