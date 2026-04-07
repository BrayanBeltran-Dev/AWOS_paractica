package mx.edu.utez.awos.u3_holamundo.repository;

import mx.edu.utez.awos.u3_holamundo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findOneByEmail(String email);
}
