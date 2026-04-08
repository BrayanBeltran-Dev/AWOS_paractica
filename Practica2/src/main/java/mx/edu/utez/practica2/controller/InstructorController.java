package mx.edu.utez.practica2.controller;

import jakarta.validation.Valid;
import mx.edu.utez.practica2.dto.CursoDTO;
import mx.edu.utez.practica2.dto.InstructorDTO;
import mx.edu.utez.practica2.model.Curso;
import mx.edu.utez.practica2.model.Instructor;
import mx.edu.utez.practica2.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public ResponseEntity<InstructorDTO> crear(@Valid @RequestBody InstructorDTO dto){

        Instructor instructor = instructorService.crearInstructor(dto);

        InstructorDTO response = new InstructorDTO();
        response.setId(instructor.getId());
        response.setNombre(instructor.getNombre());
        response.setEmail(instructor.getEmail());
        response.setEspecialidad(instructor.getEspecialidad());

        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/curso")
    public ResponseEntity<CursoDTO> agregarCurso(
            @PathVariable Long id,
            @Valid @RequestBody CursoDTO dto){
        Curso curso = instructorService.agregarCurso(id,dto);
        CursoDTO response = new CursoDTO();
        response.setTitulo(curso.getTitulo());
        response.setDescripcion(curso.getDescripcion());
        response.setDuracion(curso.getDuracion());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/curso")
    public ResponseEntity<List<CursoDTO>> obtenerCursos(
            @PathVariable Long id){
        List<Curso> cursos = instructorService.obtenerCursos(id);
        List<CursoDTO> response = cursos.stream().map(curso -> {
            CursoDTO dto = new CursoDTO();
            dto.setTitulo(curso.getTitulo());
            dto.setDescripcion(curso.getDescripcion());
            dto.setDuracion(curso.getDuracion());
            return dto;
        }).toList();
        return ResponseEntity.ok(response);
    }
}
