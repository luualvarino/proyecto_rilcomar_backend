package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Cliente;
import com.proyecto.rilcomar.entities.Usuario;
import com.proyecto.rilcomar.repos.ClienteRepository;
import com.proyecto.rilcomar.repos.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private  ClienteRepository clienteRepository;
    @Autowired
    private  UsuarioRepository usuarioRepository;

    public Cliente agregarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(int id) {
        try {
            if (!clienteRepository.existsById(id)) {
                throw new EntityNotFoundException("Cliente " + id + " no encontrado");
            }
            List<Usuario> usuarios = usuarioRepository.findByClienteId(id);
            usuarioRepository.deleteAll(usuarios);

        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al eliminar el cliente", e);
        }
    }

    public List<Cliente> obtenerClientes() { return clienteRepository.findAll(); }

    public Optional<Cliente> obtenerCliente(int id) { return clienteRepository.findById(id); }

    public Cliente editarCliente(Cliente cliente){
        if(clienteRepository.existsById(cliente.getId())){
            return clienteRepository.save(cliente);
        }else{
            throw new EntityNotFoundException("Cliente " + cliente.getId() + " no encontrado");
        }
    }
}
