package com.proyecto.rilcomar.services;

import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.entities.Pedido;
import com.proyecto.rilcomar.entities.PedidoPallet;
import com.proyecto.rilcomar.entities.PedidoPalletId;
import com.proyecto.rilcomar.enums.EstadoEnum;
import com.proyecto.rilcomar.enums.EstadoPalletEnum;
import com.proyecto.rilcomar.exceptions.NotFoundException;
import com.proyecto.rilcomar.repos.PalletRepository;
import com.proyecto.rilcomar.repos.PedidoPalletRepository;
import com.proyecto.rilcomar.repos.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import jakarta.transaction.Transactional;

import java.util.*;

@Slf4j
@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PalletRepository palletRepository;
    private final PedidoPalletRepository pedidoPalletRepository;

    public PedidoService(PedidoRepository pedidoRepository, PalletRepository palletRepository, PedidoPalletRepository pedidoPalletRepository) {
        this.pedidoRepository = pedidoRepository;
        this.palletRepository = palletRepository;
        this.pedidoPalletRepository = pedidoPalletRepository;
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

    @Transactional //las operaciones en la bd son atomicas, si ocurre un error se deshace lo previamente hecho
    public Pedido agregarPedido(Pedido pedido) {
        try{
            pedido.setFechaCreacion(new Date());
            pedido.setUltimaActualizacion(new Date());
            pedido.setUbicacion("Deposito");

            pedido = pedidoRepository.save(pedido);

            for (Pallet pallet : pedido.getPalletsAux()) {
                Pallet palletExist = palletRepository.findById(pallet.getId()).orElse(null);
                if(palletExist != null){
                    palletExist.setEstado(EstadoPalletEnum.Ocupado);

                    PedidoPalletId pedidoPalletId = new PedidoPalletId(pedido.getId(), palletExist.getId());
                    PedidoPallet pedidoPallet = new PedidoPallet(pedidoPalletId, pedido, palletExist);

                    pedidoPalletRepository.save(pedidoPallet); //necesario para poder manejar el mismo objeto en la misma sesion

                    pedido = pedidoRepository.findById(pedido.getId()).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
                    palletExist = palletRepository.findById(palletExist.getId()).orElseThrow(() -> new RuntimeException("Pallet no encontrado"));

                    pedido.getPallets().add(pedidoPallet);
                    palletExist.getHistorial().add(pedidoPallet);

                    //palletRepository.save(palletExist);
                }else{
                    throw new RuntimeException("El pallet con ID " + pallet.getId() + " no existe.");
                }
            }

            return pedido;
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al agregar el pedido", e);
        }
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
