package com.proyecto.rilcomar.mappers;

import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.enums.EstadoEnum;
import com.proyecto.rilcomar.enums.MaterialEnum;

import java.util.List;

public class PalletMapper {

    public static PalletDto buildDto(Pallet pallet){
        return PalletDto.builder()
                .id(pallet.getId())
                .estado(pallet.getEstado().name())
                .tipo(pallet.getTipo().name())
                .formato(pallet.getFormato())
                .peso(pallet.getPeso())
                .observaciones(pallet.getObservaciones())
                .ubicacion(pallet.getUbicacion())
                .historial(
                        (pallet.getHistorial() != null)
                                ? pallet.getHistorial()
                                .stream()
                                .map(pedidoPallet -> PedidoMapper.buildDto(pedidoPallet.getPedido()))
                                .toList()
                                : List.of()
                )
                .build();
    }

    public static Pallet buildEntity(PalletDto pallet){
        return Pallet.builder()
                .id(pallet.getId())
                .estado(EstadoEnum.valueOf(pallet.getEstado()))
                .tipo(MaterialEnum.valueOf(pallet.getTipo()))
                .formato(pallet.getFormato())
                .peso(pallet.getPeso())
                .observaciones(pallet.getObservaciones())
                .ubicacion(pallet.getUbicacion())
                .build();
    }
}
