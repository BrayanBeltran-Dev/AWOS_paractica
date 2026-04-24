package mx.edu.utez.productos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.productos.dto.ProductoDTO;
import mx.edu.utez.productos.model.Producto;
import mx.edu.utez.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody ProductoDTO dto) {
        Producto producto = productoService.registrar(dto);
        return new ResponseEntity<>(toDTO(producto), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDTO>> listar() {
        List<Producto> productos = productoService.listar();
        List<ProductoDTO> response = new ArrayList<>();
        for (Producto p : productos) {
            response.add(toDTO(p));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto) {
        Producto producto = productoService.actualizar(id, dto);
        if (producto == null) return ResponseEntity.badRequest().body("Producto no encontrado");
        return new ResponseEntity<>(toDTO(producto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = productoService.eliminar(id);
        if (!eliminado) return ResponseEntity.badRequest().body("Producto no encontrado");
        return ResponseEntity.ok("Producto eliminado");
    }

    private ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setPrecio(p.getPrecio());
        dto.setCantidad(p.getCantidad());
        return dto;
    }
}
