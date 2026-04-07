package mx.edu.utez.awos.u3_holamundo.repository;

import mx.edu.utez.awos.u3_holamundo.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {
    Tarea findOneByTitulo(String titulo);
}
