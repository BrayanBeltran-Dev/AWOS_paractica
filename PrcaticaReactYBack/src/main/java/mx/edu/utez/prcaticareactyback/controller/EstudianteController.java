package mx.edu.utez.prcaticareactyback.controller;

import jakarta.validation.Valid;
import mx.edu.utez.prcaticareactyback.dto.EstudianteDTO;
import mx.edu.utez.prcaticareactyback.model.Estudiante;
import mx.edu.utez.prcaticareactyback.service.EstudiateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudiateService estudiateService;

    @PostMapping
    public ResponseEntity<EstudianteDTO> registrarEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudiateService.registrar(estudianteDTO);
        EstudianteDTO response = new EstudianteDTO();
        response.setId(estudiante.getId());
        response.setNombre(estudiante.getNombre());
        response.setApellido(estudiante.getApellido());
        response.setEdad(estudiante.getEdad());

        return  new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listarEstudiantes(){
        List<Estudiante> estudiantes = estudiateService.listar();
        List<EstudianteDTO> response = estudiantes.stream().map(estudiante -> {
            EstudianteDTO dto = new EstudianteDTO();
            dto.setId(estudiante.getId());
            dto.setNombre(estudiante.getNombre());
            dto.setApellido(estudiante.getApellido());
            dto.setEdad(estudiante.getEdad());
            return dto;
        }).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
