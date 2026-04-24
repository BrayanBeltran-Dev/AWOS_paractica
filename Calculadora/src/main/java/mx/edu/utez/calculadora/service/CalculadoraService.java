package mx.edu.utez.calculadora.service;

import mx.edu.utez.calculadora.dto.CalculadoraDTO;
import mx.edu.utez.calculadora.model.Calculadora;
import mx.edu.utez.calculadora.repository.CalculadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class CalculadoraService {

    @Autowired
    private CalculadoraRepository calculadoraRepository;

    @Transactional
    public Calculadora calcular(CalculadoraDTO calculadoraDTO) {
        Calculadora calculadora = new Calculadora();
        calculadora.setNum1(calculadoraDTO.getNum1());
        calculadora.setNum2(calculadoraDTO.getNum2());
        calculadora.setOperacion(calculadoraDTO.getOperacion());
        switch (calculadoraDTO.getOperacion().toLowerCase().trim()) {
                case "sumar":
                    calculadora.setResultado(calculadora.getNum1() + calculadora.getNum2());
                break;
                case "restar":
                    calculadora.setResultado(calculadora.getNum1() - calculadora.getNum2());
                break;
                case "multiplicar":
                    calculadora.setResultado(calculadora.getNum1() * calculadora.getNum2());
                break;
                case "dividir":
                    if(calculadora.getNum2() == 0) return null;
                    calculadora.setResultado(calculadora.getNum1() / calculadora.getNum2());
                break;
                default:
                    return null;

        }
        return calculadoraRepository.save(calculadora);
    }
}
