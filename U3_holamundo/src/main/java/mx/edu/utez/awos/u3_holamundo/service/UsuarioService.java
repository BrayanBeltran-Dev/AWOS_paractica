package mx.edu.utez.awos.u3_holamundo.service;

import mx.edu.utez.awos.u3_holamundo.control.dto.CreateUserDto;
import mx.edu.utez.awos.u3_holamundo.model.Usuario;
import mx.edu.utez.awos.u3_holamundo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class  UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(CreateUserDto dto){
        Usuario usuarioExistente = usuarioRepository.findOneByEmail(dto.getCorreo());
        if (usuarioExistente != null){
            throw new RuntimeException("El email ya está registrado");
        }
        Usuario unUsuario = new Usuario();
        unUsuario.setNombre(dto.getNombre());
        unUsuario.setApellido(dto.getApellido());
        unUsuario.setEmail(dto.getCorreo());
        Usuario usuarioGuardado = usuarioRepository.save(unUsuario);
        return usuarioGuardado;
    }
    @Transactional(readOnly = true)
    public List<Usuario> consultarUsuarios(){
        return usuarioRepository.findAll();
    }
}
