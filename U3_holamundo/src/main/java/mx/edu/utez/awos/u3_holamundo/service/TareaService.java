package mx.edu.utez.awos.u3_holamundo.service;

import mx.edu.utez.awos.u3_holamundo.model.Tarea;
import mx.edu.utez.awos.u3_holamundo.model.Usuario;
import mx.edu.utez.awos.u3_holamundo.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public Tarea crearTarea(Tarea unaTarea){
        Tarea tareaExistente = tareaRepository.findOneByTitulo(unaTarea.getTitulo());

        if (tareaExistente != null){
            throw new RuntimeException("La tarea ya está registrada");
        }

        return tareaRepository.save(unaTarea);
    }
    @Transactional(readOnly = true)
    public List<Tarea> listar(){
        return tareaRepository.findAll();
    }

    public Tarea actualizarTarea(Long id, Tarea unaTarea){
        Optional<Tarea> optTarea = tareaRepository.findById(id);
        if (optTarea.isPresent()){
            Tarea tareaExistente = optTarea.get();
            tareaExistente.setTitulo(unaTarea.getTitulo());
            tareaExistente.setCompletada(unaTarea.getCompletada());
            return tareaRepository.save(tareaExistente);
        }
        throw new RuntimeException("La tarea no existe");
    }

    public Tarea buscarPorId(Long id){
        Optional<Tarea> optTarea = tareaRepository.findById(id);
        if (optTarea.isPresent()){
            return optTarea.get();
        }
        throw new RuntimeException("La tarea no existe");
    }

    public Tarea eliminarTarea(Long id){
        Optional<Tarea> optTarea = tareaRepository.findById(id);
        if (optTarea.isPresent()){
            tareaRepository.delete(optTarea.get());
            return optTarea.get();
        }
        return  null;
    }


}
