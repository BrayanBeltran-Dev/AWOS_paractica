package mx.edu.utez.prcaticareactyback.service;

import mx.edu.utez.prcaticareactyback.dto.EstudianteDTO;
import mx.edu.utez.prcaticareactyback.model.Estudiante;
import mx.edu.utez.prcaticareactyback.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudiateService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Transactional
    public Estudiante registrar(EstudianteDTO unEstudiante){
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(unEstudiante.getNombre());
        estudiante.setApellido(unEstudiante.getApellido());
        estudiante.setEdad(unEstudiante.getEdad());
        return estudianteRepository.save(estudiante);
    }
    @Transactional(readOnly = true)
    public List<Estudiante> listar(){
        return estudianteRepository.findAll();
    }
}
