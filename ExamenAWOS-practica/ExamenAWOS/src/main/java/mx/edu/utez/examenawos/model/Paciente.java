package mx.edu.utez.examenawos.model;

import jakarta.persistence.*;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Medico medico;

    @OneToOne(cascade = CascadeType.ALL)
    private ExpedienteClinico expediente;

    public Paciente() {
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public ExpedienteClinico getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteClinico expediente) {
        this.expediente = expediente;
    }
}
