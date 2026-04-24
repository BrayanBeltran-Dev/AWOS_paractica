package mx.edu.utez.prcaticareactyback.repository;

import mx.edu.utez.prcaticareactyback.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
