package mx.edu.utez.awos.u3_holamundo.control.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class CrearProductoDto {
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 3, max = 50)
    private String nombre;
    @Positive(message = "Ingresa numeros positivos")
    private Double precio;
    @NotBlank(message = "La categoria es requerido")
    @Size(min = 4, max = 50)
    private String categoria;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
