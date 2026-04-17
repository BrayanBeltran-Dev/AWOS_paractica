package mx.edu.utez.examenawos.service;

import mx.edu.utez.examenawos.dto.PacienteDTO;
import mx.edu.utez.examenawos.model.ExpedienteClinico;
import mx.edu.utez.examenawos.model.Medico;
import mx.edu.utez.examenawos.model.Paciente;
import mx.edu.utez.examenawos.repository.MedicoRepository;
import mx.edu.utez.examenawos.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public Paciente registrarPaciente(Long medicoId, PacienteDTO dto){
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        ExpedienteClinico expediente = new ExpedienteClinico();
        expediente.setTipoSangre(dto.getTipoSangre());
        expediente.setAlergias(dto.getAlergias());
        expediente.setAntecedentes(dto.getAntecedentes());

        Paciente paciente = new Paciente();
        paciente.setNombre(dto.getNombre());
        paciente.setEdad(dto.getEdad());
        paciente.setTelefono(dto.getTelefono());

        paciente.setExpediente(expediente);
        paciente.setMedico(medico);

        return pacienteRepository.save(paciente);
    }


    @Transactional
    public ExpedienteClinico consultarExpediente(Long pacienteId){
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        return paciente.getExpediente();
    }

    @Transactional
    public Paciente actualizarPaciente(Long medicoId, Long pacienteId, PacienteDTO dto){
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if(!paciente.getMedico().getId().equals(medicoId)){
            throw new RuntimeException("El paciente no pertenece a este médico");
        }

        paciente.setNombre(dto.getNombre());
        paciente.setEdad(dto.getEdad());
        paciente.setTelefono(dto.getTelefono());
        paciente.getExpediente().setTipoSangre(dto.getTipoSangre());
        paciente.getExpediente().setAlergias(dto.getAlergias());
        paciente.getExpediente().setAntecedentes(dto.getAntecedentes());

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente eliminarPaciente(Long medicoId, Long pacienteId){
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if(!paciente.getMedico().getId().equals(medicoId)){
            throw new RuntimeException("El paciente no pertenece a este médico");
        }

        pacienteRepository.delete(paciente);
        return paciente;
    }

}
