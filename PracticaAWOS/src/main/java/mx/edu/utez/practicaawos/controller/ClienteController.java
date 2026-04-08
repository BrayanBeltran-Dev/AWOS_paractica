package mx.edu.utez.practicaawos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.practicaawos.dto.ClienteDTO;
import mx.edu.utez.practicaawos.dto.ReservaDTO;
import mx.edu.utez.practicaawos.model.Cliente;
import mx.edu.utez.practicaawos.model.Reserva;
import mx.edu.utez.practicaawos.repository.ClienteRepository;
import mx.edu.utez.practicaawos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO dto){
        Cliente cliente = clienteService.crearCliente(dto);
        ClienteDTO response = new ClienteDTO();
        response.setId(cliente.getId());
        response.setNombre(dto.getNombre());
        response.setEmail(dto.getEmail());
        response.setTelefono(dto.getTelefono());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/reserva")
    public ResponseEntity<ReservaDTO> agregarReserva(
            @PathVariable Long id,
            @Valid @RequestBody ReservaDTO dto){
        Reserva reserva = clienteService.agregarReserva(id, dto);
        ReservaDTO response = new ReservaDTO();
        response.setFecha(reserva.getFecha());
        response.setCantidadPersonas(reserva.getCantidadPersonas());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/reserva")
    public ResponseEntity<List<ReservaDTO>> obtenerReservas(@Valid @PathVariable Long id){
        List<Reserva> reservas = clienteService.obtenerReservas(id);
        List<ReservaDTO> response = reservas.stream().map(reserva -> {
            ReservaDTO dto = new ReservaDTO();
            dto.setFecha(reserva.getFecha());
            dto.setCantidadPersonas(reserva.getCantidadPersonas());
            return dto;
        }).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/reservas/{reservaId}")
    public ResponseEntity<Reserva> actualizarReserva(
            @PathVariable Long reservaId,
            @RequestBody ReservaDTO dto){

        Reserva reservaActualizada = clienteService.actualizarReserva(reservaId, dto);
        return ResponseEntity.ok(reservaActualizada);
    }

    @DeleteMapping("/reservas/{reservaId}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long reservaId){

        clienteService.eliminarReserva(reservaId);
        return ResponseEntity.noContent().build();
    }
}
