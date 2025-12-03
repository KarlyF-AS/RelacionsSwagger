package org.example.services;

import jakarta.transaction.Transactional;
import org.example.model.Titores;
import org.example.repository.TitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitoresServices {

    private final TitorRepository titorRepository;

    @Autowired
    public TitoresServices(TitorRepository titorRepository) {
        this.titorRepository = titorRepository;
    }

    @Transactional
    public Titores crearOActualizarTutores(Titores titores) {
        return titorRepository.save(titores);
    }

    public List<Titores> obtenerTodosLosTutores() {
        return titorRepository.findAll();
    }


    public Optional<Titores> obtenerTutorPorId(Long id) {
        return titorRepository.findById(id);
    }

    public Optional<Titores> obtenerTutorConAlumnos(Long id) {
        return titorRepository.findById(id);
    }

    public void eliminarTutorPorId(Long id) {
        titorRepository.deleteById(id);
    }
}
