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
    public PacienteDTO actualizarExpediente(Long medicoId, Long pacienteId, PacienteDTO dto){

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if(!paciente.getMedico().getId().equals(medicoId)){
            throw new RuntimeException("El paciente no pertenece a este médico");
        }

        if(paciente.getExpediente() == null){
            throw new RuntimeException("El paciente no tiene expediente clínico");
        }

        ExpedienteClinico expediente = paciente.getExpediente();


        if(dto.getTipoSangre() != null){
            expediente.setTipoSangre(dto.getTipoSangre());
        }

        if(dto.getAlergias() != null){
            expediente.setAlergias(dto.getAlergias());
        }

        if(dto.getAntecedentes() != null){
            expediente.setAntecedentes(dto.getAntecedentes());
        }

        pacienteRepository.save(paciente);

        PacienteDTO response = new PacienteDTO();
        response.setId(paciente.getId());
        response.setNombre(paciente.getNombre());
        response.setEdad(paciente.getEdad());
        response.setTelefono(paciente.getTelefono());

        response.setTipoSangre(expediente.getTipoSangre());
        response.setAlergias(expediente.getAlergias());
        response.setAntecedentes(expediente.getAntecedentes());

        return response;
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

    @Transactional
    public PacienteDTO crearExpediente(Long pacienteId, PacienteDTO dto){

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if(paciente.getExpediente() != null){
            throw new RuntimeException("El paciente ya tiene expediente clínico");
        }

        ExpedienteClinico expediente = new ExpedienteClinico();
        expediente.setTipoSangre(dto.getTipoSangre());
        expediente.setAlergias(dto.getAlergias());
        expediente.setAntecedentes(dto.getAntecedentes());

        paciente.setExpediente(expediente);

        pacienteRepository.save(paciente);

        PacienteDTO response = new PacienteDTO();
        response.setId(paciente.getId());
        response.setNombre(paciente.getNombre());
        response.setEdad(paciente.getEdad());
        response.setTelefono(paciente.getTelefono());
        response.setTipoSangre(expediente.getTipoSangre());

        return response;
    }

    @Transactional(readOnly = true)
    public PacienteDTO obtenerPacientePorMedico(Long medicoId, Long pacienteId){

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if(!paciente.getMedico().getId().equals(medicoId)){
            throw new RuntimeException("El paciente no pertenece a este médico");
        }

        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setEdad(paciente.getEdad());
        dto.setTelefono(paciente.getTelefono());

        if(paciente.getExpediente() != null){
            dto.setTipoSangre(paciente.getExpediente().getTipoSangre());
            dto.setAlergias(paciente.getExpediente().getAlergias());
            dto.setAntecedentes(paciente.getExpediente().getAntecedentes());
        }

        return dto;
    }
    @Transactional
    public PacienteDTO transferirPaciente(Long medicoOrigenId, Long pacienteId, Long medicoDestinoId){

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Medico medicoDestino = medicoRepository.findById(medicoDestinoId)
                .orElseThrow(() -> new RuntimeException("Médico destino no encontrado"));

        if(!paciente.getMedico().getId().equals(medicoOrigenId)){
            throw new RuntimeException("El paciente no pertenece al médico origen");
        }

        if(medicoOrigenId.equals(medicoDestinoId)){
            throw new RuntimeException("El médico destino no puede ser el mismo");
        }

        paciente.setMedico(medicoDestino);

        pacienteRepository.save(paciente);

        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setEdad(paciente.getEdad());
        dto.setTelefono(paciente.getTelefono());

        return dto;
    }

}
