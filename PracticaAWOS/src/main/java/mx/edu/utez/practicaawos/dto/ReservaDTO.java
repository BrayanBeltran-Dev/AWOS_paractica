package mx.edu.utez.practicaawos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReservaDTO {
    @NotBlank(message = "Fecha obligatoria")
    private String fecha;

    @NotNull(message = "Cantidad de personas obligatoria")
    private Integer cantidadPersonas;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
}
