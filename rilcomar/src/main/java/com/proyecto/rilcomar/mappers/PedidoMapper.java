package com.proyecto.rilcomar.mappers;

import com.proyecto.rilcomar.dtos.PedidoDto;
import com.proyecto.rilcomar.entities.Pedido;

public class PedidoMapper {

    public static PedidoDto buildDto(Pedido pedido) {
        return PedidoDto.builder()
                .id(pedido.getId())
                .estado(pedido.getEstado())
                .cliente(ClienteMapper.buildDto(pedido.getCliente()))
                .fechaCreacion(pedido.getFechaCreacion())
                .fechaEntrega(pedido.getFechaEntrega())
                .ultimaActualizacion(pedido.getUltimaActualizacion())
                .ubicacion(pedido.getUbicacion())
                .pallets(
                        pedido.getPallets()
                                .stream()
                                .map(pedidoPallet -> PalletMapper.buildDto(pedidoPallet.getPallet()))
                                .toList()
                )
                .build();
    }

    public static Pedido buildEntity(PedidoDto pedido){
        return Pedido.builder()
                .id(pedido.getId())
                .estado(pedido.getEstado())
                .cliente(ClienteMapper.buildEntity(pedido.getCliente()))
                .fechaCreacion(pedido.getFechaCreacion())
                .fechaEntrega(pedido.getFechaEntrega())
                .ultimaActualizacion(pedido.getUltimaActualizacion())
                .ubicacion(pedido.getUbicacion())
                .build();
    }
}
