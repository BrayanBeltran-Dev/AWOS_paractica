package mx.edu.utez.awos.u3_holamundo.service;

import mx.edu.utez.awos.u3_holamundo.control.dto.CrearProductoDto;
import mx.edu.utez.awos.u3_holamundo.model.Producto;
import mx.edu.utez.awos.u3_holamundo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crearProducto(CrearProductoDto dto) {
        Producto unProducto = new Producto();
        unProducto.setNombre(dto.getNombre());
        unProducto.setPrecio(dto.getPrecio());
        unProducto.setCategoria(dto.getCategoria());
        return productoRepository.save(unProducto);
    }

    public Producto buscarPorId(Long id) {
        Optional<Producto> optProducto = productoRepository.findById(id);
        if (optProducto.isPresent()) {
            return optProducto.get();
        }
        throw new RuntimeException("El producto no existe");
    }
    @Transactional(readOnly = true)
    public Page<Producto> listar(String categoria, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return productoRepository.findByCategoria(categoria, pageable);
    }
}