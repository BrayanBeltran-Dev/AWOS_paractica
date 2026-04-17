package mx.edu.utez.examenawos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.examenawos.dto.PacienteDTO;
import mx.edu.utez.examenawos.model.ExpedienteClinico;
import mx.edu.utez.examenawos.model.Paciente;
import mx.edu.utez.examenawos.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/{id}/pacientes")
    public ResponseEntity<PacienteDTO> registrarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        Paciente paciente = pacienteService.registrarPaciente(id, dto);

        PacienteDTO response = new PacienteDTO();
        response.setId(paciente.getId());
        response.setNombre(paciente.getNombre());
        response.setEdad(paciente.getEdad());
        response.setTelefono(paciente.getTelefono());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/expediente")
    public ResponseEntity<PacienteDTO> expedientePaciente(@PathVariable Long id) {
        ExpedienteClinico expediente =  pacienteService.consultarExpediente(id);

        PacienteDTO response = new PacienteDTO();
        response.setId(expediente.getId());
        response.setTipoSangre(expediente.getTipoSangre());
        response.setAlergias(expediente.getAlergias());
        response.setAntecedentes(expediente.getAntecedentes());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{medicoId}/pacientes/{pacienteId}")
    public ResponseEntity<PacienteDTO> actualizarPaciente(@PathVariable Long medicoId, @PathVariable Long pacienteId, @Valid @RequestBody PacienteDTO dto){
        Paciente paciente = pacienteService.actualizarPaciente(medicoId, pacienteId, dto);

        PacienteDTO response = new PacienteDTO();
        response.setId(paciente.getId());
        response.setNombre(paciente.getNombre());
        response.setEdad(paciente.getEdad());
        response.setTelefono(paciente.getTelefono());
        response.setTipoSangre(paciente.getExpediente().getTipoSangre());
        response.setAlergias(paciente.getExpediente().getAlergias());
        response.setAntecedentes(paciente.getExpediente().getAntecedentes());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{medicoId}/pacientes/{pacienteId}/expediente")
    public ResponseEntity<PacienteDTO> actualizarExpediente(
            @PathVariable Long medicoId,
            @PathVariable Long pacienteId,
            @Valid @RequestBody PacienteDTO dto){

        PacienteDTO response = pacienteService.actualizarExpediente(medicoId, pacienteId, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{medicoId}/pacientes/{pacienteId}")
    public ResponseEntity<PacienteDTO> eliminarPaciente(@PathVariable Long medicoId, @PathVariable Long pacienteId){
        Paciente paciente = pacienteService.eliminarPaciente(medicoId, pacienteId);

        PacienteDTO response = new PacienteDTO();
        response.setId(paciente.getId());
        response.setNombre(paciente.getNombre());
        response.setEdad(paciente.getEdad());
        response.setTelefono(paciente.getTelefono());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pacientes/{pacienteId}/expediente")
    public ResponseEntity<PacienteDTO> crearExpediente(
            @PathVariable Long pacienteId,
            @Valid @RequestBody PacienteDTO dto){

        PacienteDTO response = pacienteService.crearExpediente(pacienteId, dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{medicoId}/pacientes/{pacienteId}")
    public ResponseEntity<PacienteDTO> obtenerPaciente(
            @PathVariable Long medicoId,
            @PathVariable Long pacienteId){

        PacienteDTO response = pacienteService.obtenerPacientePorMedico(medicoId, pacienteId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{medicoOrigenId}/pacientes/{pacienteId}/transferir/{medicoDestinoId}")
    public ResponseEntity<PacienteDTO> transferirPaciente(
            @PathVariable Long medicoOrigenId,
            @PathVariable Long pacienteId,
            @PathVariable Long medicoDestinoId){

        PacienteDTO response = pacienteService.transferirPaciente(
                medicoOrigenId, pacienteId, medicoDestinoId);

        return ResponseEntity.ok(response);
    }

}
