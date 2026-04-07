package mx.edu.utez.awos.u3_holamundo.control;

import jakarta.validation.Valid;
import mx.edu.utez.awos.u3_holamundo.control.dto.CrearProductoDto;
import mx.edu.utez.awos.u3_holamundo.model.Producto;
import mx.edu.utez.awos.u3_holamundo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<Producto>> listar(@RequestParam String categoria,
                                                 @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(productoService.listar(categoria, page));
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@Valid @RequestBody CrearProductoDto dto) {
        return ResponseEntity.ok(productoService.crearProducto(dto));
    }
}