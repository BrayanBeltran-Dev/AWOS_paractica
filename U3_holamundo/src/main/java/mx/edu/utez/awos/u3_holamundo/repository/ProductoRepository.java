package mx.edu.utez.awos.u3_holamundo.repository;

import mx.edu.utez.awos.u3_holamundo.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    Producto findByCategoria(String categoria);
    Page<Producto> findByCategoria(String categoria, Pageable pageable);
}
