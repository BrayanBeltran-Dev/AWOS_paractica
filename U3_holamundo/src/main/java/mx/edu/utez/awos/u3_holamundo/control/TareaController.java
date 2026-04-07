package mx.edu.utez.awos.u3_holamundo.control;

import mx.edu.utez.awos.u3_holamundo.model.Tarea;
import mx.edu.utez.awos.u3_holamundo.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    @Autowired
    TareaService tareaService;

    @GetMapping
    public List<Tarea> listar(){
        List<Tarea> tareas =  tareaService.listar();
        return tareas;
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea unaTarea){
        Tarea tareaNueva = tareaService.crearTarea(unaTarea);
        return tareaNueva;
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id,@RequestBody Tarea unaTarea){
            Tarea tareaActualizada = tareaService.actualizarTarea(id, unaTarea);
            return tareaActualizada;
    }
    @DeleteMapping("/{id}")
    public Tarea eliminarTarea(@PathVariable Long id){
      return  tareaService.eliminarTarea(id);
    }
}
