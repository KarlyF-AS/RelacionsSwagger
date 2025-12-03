package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.model.Titores;
import org.example.repository.TitorRepository;
import org.example.services.TitoresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(TitorController.MAPPING)
public class TitorController {

    public static final String MAPPING = "/titor";

    @Autowired
    private TitorRepository titorRepository;

    @Autowired
    private TitoresServices titoresServices;

    @Operation(summary = "Método que saúda")
    @PostMapping("/saudo")
    public String saudo() {
        return "Boas";
    }

    @Operation(summary = "Crear un nuevo titores")
    @PostMapping("/")
    public Titores crearTutor(@RequestBody Titores titores) {
        return titoresServices.crearOActualizarTutores(titores);
    }

    @Operation(summary = "Obtener todos los titores")
    @GetMapping("/")
    public List<Titores> obtenerTodosLosTutores() {
        return titoresServices.obtenerTodosLosTutores();
    }

    @Operation(summary = "Obtener titores por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Titores> obtenerTutorPorId(@PathVariable Long id) {
        Optional<Titores> titores = titoresServices.obtenerTutorPorId(id);
        return titores.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // NUEVO ENDPOINT: Obtener titor con sus alumnos
    @Operation(summary = "Obtener un titor y todos sus alumnos")
    @GetMapping("/{id}/con-alumnos")
    public ResponseEntity<Titores> obtenerTutorConAlumnos(@PathVariable Long id) {
        Optional<Titores> titores = titoresServices.obtenerTutorConAlumnos(id);
        return titores.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar un titor")
    @PutMapping("/{id}")
    public ResponseEntity<Titores> actualizarTutor(@PathVariable Long id, @RequestBody Titores titoresDetails) {
        Optional<Titores> titoresOptional = titoresServices.obtenerTutorPorId(id);
        if (titoresOptional.isPresent()) {
            Titores titores = titoresOptional.get();
            titores.setNome(titoresDetails.getNome());
            titores.setApelidos(titoresDetails.getApelidos()); // Actualizar apellidos también

            Titores titoresActualizado = titoresServices.crearOActualizarTutores(titores);
            return ResponseEntity.ok(titoresActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un titor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTutorPorId(@PathVariable Long id) {
        Optional<Titores> titores = titoresServices.obtenerTutorPorId(id);
        if (titores.isPresent()) {
            // Verificar si tiene alumnos antes de eliminar
            if (!titores.get().getAlumnos().isEmpty()) {
                // Podría devolver un error 409 Conflict o similar
                return ResponseEntity.badRequest().build();
            }
            titoresServices.eliminarTutorPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}