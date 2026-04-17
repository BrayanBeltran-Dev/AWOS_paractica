package mx.edu.utez.examenawos.service;

import mx.edu.utez.examenawos.dto.MedicoDTO;
import mx.edu.utez.examenawos.dto.PacienteDTO;
import mx.edu.utez.examenawos.model.Medico;
import mx.edu.utez.examenawos.model.Paciente;
import mx.edu.utez.examenawos.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Medico actualizarMedico(Long medicoId, MedicoDTO dto){
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        medico.setNombre(dto.getNombre());
        medico.setEspecialidad(dto.getEspecialidad());
        medico.setCedula(dto.getCedula());

        return medicoRepository.save(medico);
    }
    @Transactional
    public Medico eliminarMedico(Long medicoId){
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
        medicoRepository.delete(medico);
        return medico;
    }
}
