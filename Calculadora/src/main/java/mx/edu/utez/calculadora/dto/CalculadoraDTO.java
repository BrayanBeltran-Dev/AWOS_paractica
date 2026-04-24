package mx.edu.utez.calculadora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CalculadoraDTO {

    private Long id;

    @NotNull(message = "El numero es obligatorio")
    private Double num1;

    @NotNull(message = "El numero es obligatorio")
    private Double num2;

    @NotBlank(message = "La operacion es obligatoria")
    private String operacion;

    private Double resultado;


    public CalculadoraDTO() {
    }

    public CalculadoraDTO(Long id, Double num1, Double num2, String operacion, Double resultado) {
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
