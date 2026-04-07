package mx.edu.utez.awos.u3_holamundo.control;

import jakarta.validation.Valid;
import mx.edu.utez.awos.u3_holamundo.control.dto.CreateUserDto;
import mx.edu.utez.awos.u3_holamundo.model.Usuario;
import mx.edu.utez.awos.u3_holamundo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public List<Usuario> consultarUsuarios(){
        List<Usuario> usuarios = usuarioService.consultarUsuarios();
        return usuarios;
    }
    @PostMapping
    public ResponseEntity<Object> registrarUsuario(@Valid @RequestBody CreateUserDto dto){
        Usuario usuarioNuevo = usuarioService.guardarUsuario(dto);
        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("status","201");
        respuesta.put("message","Usuario registrado exitosamente");
        respuesta.put("data", usuarioNuevo);

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}
