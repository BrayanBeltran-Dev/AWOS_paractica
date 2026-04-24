package mx.edu.utez.calculadora.repository;

import mx.edu.utez.calculadora.model.Calculadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculadoraRepository extends JpaRepository<Calculadora, Long> {
}
