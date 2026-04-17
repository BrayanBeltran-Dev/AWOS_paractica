package mx.edu.utez.examenawos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.examenawos.dto.MedicoDTO;
import mx.edu.utez.examenawos.dto.PacienteDTO;
import mx.edu.utez.examenawos.model.Medico;
import mx.edu.utez.examenawos.model.Paciente;
import mx.edu.utez.examenawos.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoDTO> registrarMedico(@Valid @RequestBody MedicoDTO dto){
        Medico medico = medicoService.registrarMedico(dto);

        MedicoDTO response = new MedicoDTO();
        response.setId(medico.getId());
        response.setNombre(medico.getNombre());
        response.setEspecialidad(medico.getEspecialidad());
        response.setCedula(medico.getCedula());

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    @GetMapping("/{id}/pacientes")
    public ResponseEntity<List<PacienteDTO>> listarPacientes(@PathVariable Long id){
        List<Paciente> pacientes = medicoService.listarPacientes(id);

        List<PacienteDTO> response = pacientes.stream().map(paciente ->{
            PacienteDTO dto = new PacienteDTO();
            dto.setId(paciente.getId());
            dto.setNombre(paciente.getNombre());
            dto.setEdad(paciente.getEdad());
            dto.setTelefono(paciente.getTelefono());
            return dto;
        }).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> actualizarMedico(@PathVariable Long id,@Valid @RequestBody MedicoDTO dto){
        Medico medico = medicoService.actualizarMedico(id,dto);
        MedicoDTO response = new MedicoDTO();
        response.setId(medico.getId());
        response.setNombre(medico.getNombre());
        response.setEspecialidad(medico.getEspecialidad());
        response.setCedula(medico.getCedula());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoDTO> eliminarMedico(@PathVariable Long id){
        Medico medico = medicoService.eliminarMedico(id);
        MedicoDTO response = new MedicoDTO();
        response.setId(medico.getId());
        response.setNombre(medico.getNombre());
        response.setEspecialidad(medico.getEspecialidad());
        response.setCedula(medico.getCedula());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
