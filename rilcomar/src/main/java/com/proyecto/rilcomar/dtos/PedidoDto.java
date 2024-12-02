package com.proyecto.rilcomar.dtos;

import com.proyecto.rilcomar.entities.Pedido;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {
    private int id;
    private String estado;
    private ClienteDto cliente;
    private Date fechaCreacion;
    private Date fechaEntrega;
    private Date ultimaActualizacion;
    private String ubicacion;
    private List<PalletDto> pallets;

    public static PedidoDto build(Pedido pedido) {
        return PedidoDto.builder()
                .id(pedido.getId())
                .estado(pedido.getEstado())
                .cliente(ClienteDto.build(pedido.getCliente()))
                .fechaCreacion(pedido.getFechaCreacion())
                .fechaEntrega(pedido.getFechaEntrega())
                .ultimaActualizacion(pedido.getUltimaActualizacion())
                .ubicacion(pedido.getUbicacion())
                .pallets(
                        pedido.getPallets()
                                .stream()
                                .map(pedidoPallet -> PalletDto.build(pedidoPallet.getPallet()))
                                .toList()
                )
                .build();
    }
}
