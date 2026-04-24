package mx.edu.utez.calculadora.model;

import jakarta.persistence.*;

@Entity
@Table(name = "calculadoras")
public class Calculadora {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double num1;

    @Column(nullable = false)
    private Double num2;

    @Column(nullable = false)
    private String operacion;

    @Column(nullable = false)
    private Double resultado;

    public Calculadora() {}

    public Calculadora(Long id, Double num1, Double num2, String operacion, Double resultado) {
        this.id = id;
        this.num1 = num1;
        this.num2 = num2;
        this.operacion = operacion;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public Double getNum2() {
        return num2;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
}
