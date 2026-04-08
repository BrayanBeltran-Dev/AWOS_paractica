package mx.edu.utez.practica2.service;

import mx.edu.utez.practica2.dto.CursoDTO;
import mx.edu.utez.practica2.dto.InstructorDTO;
import mx.edu.utez.practica2.model.Curso;
import mx.edu.utez.practica2.model.Instructor;
import mx.edu.utez.practica2.repository.CursoRepository;
import mx.edu.utez.practica2.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    public Instructor crearInstructor(InstructorDTO dto){
        Instructor instructor = new Instructor();
        instructor.setNombre(dto.getNombre());
        instructor.setEmail(dto.getEmail());
        instructor.setEspecialidad(dto.getEspecialidad());

        return instructorRepository.save(instructor);
    }
    @Transactional
    public Curso agregarCurso(Long instructorId, CursoDTO dto){
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        Curso curso = new Curso();
        curso.setTitulo(dto.getTitulo());
        curso.setDescripcion(dto.getDescripcion());
        curso.setDuracion(dto.getDuracion());

        curso.setInstructor(instructor);
        instructor.getCurso().add(curso);
        return cursoRepository.save(curso);
    }
    @Transactional
    public List<Curso> obtenerCursos(Long instructorID){
        Instructor instructor = instructorRepository.findById(instructorID)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        return instructor.getCurso();
    }

}
