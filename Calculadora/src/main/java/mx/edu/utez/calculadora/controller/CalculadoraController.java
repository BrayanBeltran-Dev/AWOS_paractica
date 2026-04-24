package mx.edu.utez.calculadora.controller;

import jakarta.validation.Valid;
import mx.edu.utez.calculadora.dto.CalculadoraDTO;
import mx.edu.utez.calculadora.model.Calculadora;
import mx.edu.utez.calculadora.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {

    @Autowired
    private CalculadoraService calculadoraService;

    @PostMapping("/calcular")
    public ResponseEntity<?> calcular(@Valid @RequestBody CalculadoraDTO dto) {
        Calculadora calculadora = calculadoraService.calcular(dto);
        if (calculadora == null) {
            return ResponseEntity.badRequest().body("Error");
        }
        CalculadoraDTO response = new CalculadoraDTO();
        response.setId(calculadora.getId());
        response.setNum1(calculadora.getNum1());
        response.setNum2(calculadora.getNum2());
        response.setOperacion(calculadora.getOperacion());
        response.setResultado(calculadora.getResultado());
        return ResponseEntity.ok(response);

    }
}
