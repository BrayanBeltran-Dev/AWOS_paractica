package mx.edu.utez.productos.service;

import mx.edu.utez.productos.dto.ProductoDTO;
import mx.edu.utez.productos.model.Producto;
import mx.edu.utez.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Producto registrar(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidad(dto.getCantidad());
        return productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Transactional
    public Producto actualizar(Long id, ProductoDTO dto) {
        Optional<Producto> optional = productoRepository.findById(id);
        if (optional.isEmpty()) return null;
        Producto producto = optional.get();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidad(dto.getCantidad());
        return productoRepository.save(producto);
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (!productoRepository.existsById(id)) return false;
        productoRepository.deleteById(id);
        return true;
    }
}
