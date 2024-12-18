package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Pedido;
import com.proyecto.rilcomar.repos.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido agregarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(int id) {
        try {
            if (pedidoRepository.existsById(id))
                pedidoRepository.deleteById(id);
            else
                throw new EntityNotFoundException("Pallet " + id + " no encontrado");
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al eliminar el pallet", e);
        }
    }

    public List<Pedido> obtenerPedidos() { return pedidoRepository.findAll(); }

    public Optional<Pedido> obtenerPedido(int id) { return pedidoRepository.findById(id); }
}
