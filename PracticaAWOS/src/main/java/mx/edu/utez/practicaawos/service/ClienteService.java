package mx.edu.utez.practicaawos.service;

import mx.edu.utez.practicaawos.dto.ClienteDTO;
import mx.edu.utez.practicaawos.dto.ReservaDTO;
import mx.edu.utez.practicaawos.model.Cliente;
import mx.edu.utez.practicaawos.model.Reserva;
import mx.edu.utez.practicaawos.repository.ClienteRepository;
import mx.edu.utez.practicaawos.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    public Cliente crearCliente(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Reserva agregarReserva(Long clienteID, ReservaDTO dto){
        Cliente cliente = clienteRepository.findById(clienteID)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Reserva reserva = new Reserva();
        reserva.setFecha(dto.getFecha());
        reserva.setCantidadPersonas(dto.getCantidadPersonas());

        reserva.setCliente(cliente);
        cliente.getReserva().add(reserva);
        return reservaRepository.save(reserva);
    }

    @Transactional
    public List<Reserva> obtenerReservas(Long clienteID){
        Cliente cliente = clienteRepository.findById(clienteID)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return cliente.getReserva();
    }

}
