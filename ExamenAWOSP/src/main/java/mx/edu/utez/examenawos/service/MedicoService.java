package mx.edu.utez.examenawos.service;

import mx.edu.utez.examenawos.dto.MedicoDTO;
import mx.edu.utez.examenawos.dto.PacienteDTO;
import mx.edu.utez.examenawos.model.Medico;
import mx.edu.utez.examenawos.model.Paciente;
import mx.edu.utez.examenawos.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public Medico registrarMedico(MedicoDTO dto){
        Medico medico = new Medico();
        medico.setNombre(dto.getNombre());
        medico.setEspecialidad(dto.getEspecialidad());
        medico.setCedula(dto.getCedula());

        return medicoRepository.save(medico);
    }

    @Transactional(readOnly = true)
    public List<Paciente> listarPacientes(Long medicoId){
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        return medico.getPacientes();
    }
    @Transactional
    public Medico eliminarMedico(Long medicoId){
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        if(!medico.getPacientes().isEmpty()){
            throw new RuntimeException("No se puede eliminar un médico con pacientes registrados");
        }

        medicoRepository.delete(medico);
        return medico;
    }
    @Transactional
    public Page<MedicoDTO> listarMedicos(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Medico> medicos = medicoRepository.findAll(pageable);

        return medicos.map(medico -> {
            MedicoDTO dto = new MedicoDTO();
            dto.setId(medico.getId());
            dto.setNombre(medico.getNombre());
            dto.setEspecialidad(medico.getEspecialidad());
            dto.setCedula(medico.getCedula());
            return dto;
        });
    }



}
