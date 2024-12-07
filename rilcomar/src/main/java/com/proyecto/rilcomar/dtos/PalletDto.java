package com.proyecto.rilcomar.dtos;

import com.proyecto.rilcomar.entities.Pallet;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PalletDto {
    private int id;
    private String estado;
    private String tipo;
    private boolean estaDisponible;
    private String formato;
    private double peso;
    private String dimensiones;
    private String observaciones;
    private String ubicacion;
    private List<PedidoDto> historial;

    public static PalletDto build(Pallet pallet){
        return PalletDto.builder()
                .id(pallet.getId())
                .estado(pallet.getEstado())
                .tipo(pallet.getTipo())
                .estaDisponible(pallet.isEstaDisponible())
                .ubicacion(pallet.getUbicacion())
                .historial(
                        pallet.getHistorial()
                                .stream()
                                .map(pedidoPallet -> PedidoDto.build(pedidoPallet.getPedido()))
                                .toList()
                )
                .build();
    }
}
