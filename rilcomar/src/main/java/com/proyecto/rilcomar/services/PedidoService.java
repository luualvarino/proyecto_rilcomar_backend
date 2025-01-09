package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.entities.Pedido;
import com.proyecto.rilcomar.entities.PedidoPallet;
import com.proyecto.rilcomar.entities.PedidoPalletId;
import com.proyecto.rilcomar.enums.EstadoEnum;
import com.proyecto.rilcomar.repos.PalletRepository;
import com.proyecto.rilcomar.repos.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PalletRepository palletRepository;

    public PedidoService(PedidoRepository pedidoRepository, PalletRepository palletRepository) {
        this.pedidoRepository = pedidoRepository;
        this.palletRepository = palletRepository;
    }

    public Pedido agregarPedido(Pedido pedido){
        pedido.setFechaCreacion(new Date());
        pedido.setUltimaActualizacion(new Date());
        pedido.setEstado(EstadoEnum.Creado);
        pedido.setUbicacion("Deposito");
        for(Pallet pallet: pedido.getPalletsAux()){
            Pallet palletExist = palletRepository.findById(pallet.getId()).orElse(null);
            assert palletExist != null;
            PedidoPalletId pedidoPalletId = new PedidoPalletId(pedido.getId(), palletExist.getId());
            PedidoPallet pedidoPallet = new PedidoPallet(pedidoPalletId, pedido, palletExist);
            pedido.getPallets().add(pedidoPallet);
        }
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(int id) {
        try {
            if (pedidoRepository.existsById(id))
                pedidoRepository.deleteById(id);
            else
                throw new EntityNotFoundException("Pedido " + id + " no encontrado");
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al eliminar el pedido", e);
        }
    }

    public List<Pedido> obtenerPedidos(String estado) {
        EstadoEnum estadoEnum = estado != null ? EstadoEnum.valueOf(estado) : null;
        return pedidoRepository.findAllByEstado(estadoEnum);
    }

    public Optional<Pedido> obtenerPedido(int id) { return pedidoRepository.findById(id); }
}
