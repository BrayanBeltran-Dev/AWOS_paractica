package mx.edu.utez.examenawos.dto;

import jakarta.validation.constraints.NotBlank;

public class MedicoDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @NotBlank(message = "La cédula es obligatoria")
    private String cedula;

    public MedicoDTO() {
    }

    public MedicoDTO(Long id, String nombre, String especialidad, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cedula = cedula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
