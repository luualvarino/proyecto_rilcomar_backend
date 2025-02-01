package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.entities.Pedido;
import com.proyecto.rilcomar.entities.PedidoPallet;
import com.proyecto.rilcomar.entities.PedidoPalletId;
import com.proyecto.rilcomar.enums.EstadoEnum;
import com.proyecto.rilcomar.enums.EstadoPalletEnum;
import com.proyecto.rilcomar.exceptions.NotFoundException;
import com.proyecto.rilcomar.repos.PalletRepository;
import com.proyecto.rilcomar.repos.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Slf4j
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PalletRepository palletRepository;

    public PedidoService(PedidoRepository pedidoRepository, PalletRepository palletRepository) {
        this.pedidoRepository = pedidoRepository;
        this.palletRepository = palletRepository;
    }

    public List<Pedido> obtenerPedidos(String estado) {
        EstadoEnum estadoEnum = estado != null ? EstadoEnum.valueOf(estado) : null;
        if(estadoEnum != null && estadoEnum.equals(EstadoEnum.Finalizado)){
            return pedidoRepository.findByEstadoNot(estadoEnum);
        }
        return pedidoRepository.findAllByEstado(estadoEnum);
    }

    public Pedido obtenerPedido(int id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro un Pedido con el id " + id));
    }

    public Pedido agregarPedido(Pedido pedido) {
        pedido.setFechaCreacion(new Date());
        pedido.setUltimaActualizacion(new Date());
        pedido.setEstado(EstadoEnum.Creado);
        pedido.setUbicacion("Deposito");
        for (Pallet pallet : pedido.getPalletsAux()) {
            Pallet palletExist = palletRepository.findById(pallet.getId()).orElse(null);
            assert palletExist != null;
            palletExist.setEstado(EstadoPalletEnum.Ocupado);
            PedidoPalletId pedidoPalletId = new PedidoPalletId(pedido.getId(), palletExist.getId());
            PedidoPallet pedidoPallet = new PedidoPallet(pedidoPalletId, pedido, palletExist);
            pedido.getPallets().add(pedidoPallet);
            palletRepository.save(palletExist);
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido editarPedido(Pedido pedido){
        Pedido pedidoExistente =  pedidoRepository.findById(pedido.getId())
                .orElseThrow(() -> new EntityNotFoundException("Pedido " + pedido.getId() + " no encontrado"));

        if (pedidoExistente.getEstado() == EstadoEnum.Finalizado) {
            throw new IllegalStateException("No se puede actualizar un pedido finalizado.");
        }

        if (pedido.getEstado() == null) {
            throw new IllegalArgumentException("El estado no puede ser null");
        }
        if(pedido.getEstado() == EstadoEnum.Finalizado){
            for(Pallet pallet : pedido.getPalletsAux()){
                Pallet palletExist = palletRepository.findById(pallet.getId()).orElse(null);
                assert palletExist != null;
                palletExist.setEstado(EstadoPalletEnum.Libre);
                palletRepository.save(palletExist);
            }
        }

        pedido.setUltimaActualizacion(new Date());
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(int id) {
        try {
            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Pedido " + id + " no encontrado"));

            if (pedido.getEstado() == EstadoEnum.Creado || pedido.getEstado() == EstadoEnum.Finalizado) {
                pedidoRepository.deleteById(id);
            }else
                throw new IllegalStateException("No se puede eliminar un pedido con estado 'Creado' o 'Finalizado'.");

        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al eliminar el pedido", e);
        }
    }

    public List<Pedido> obtenerPedidosXCliente(int clienteId, String estado) {
        if ((estado == null || estado.isEmpty()))
            return pedidoRepository.findAllByCliente(clienteId);
        else {
            EstadoEnum estadoEnum = EstadoEnum.valueOf(estado);
            return pedidoRepository.findAllByClienteEstado(clienteId, estadoEnum);
        }
    }
}
