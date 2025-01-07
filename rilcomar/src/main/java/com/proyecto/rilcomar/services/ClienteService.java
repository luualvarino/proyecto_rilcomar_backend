package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Cliente;
import com.proyecto.rilcomar.repos.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente agregarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(int id) {
        try {
            if (clienteRepository.existsById(id))
                clienteRepository.deleteById(id);
            else
                throw new EntityNotFoundException("Cliente " + id + " no encontrado");
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al eliminar el cliente", e);
        }
    }

    public List<Cliente> obtenerClientes() { return clienteRepository.findAll(); }

    public Optional<Cliente> obtenerCliente(int id) { return clienteRepository.findById(id); }
}
