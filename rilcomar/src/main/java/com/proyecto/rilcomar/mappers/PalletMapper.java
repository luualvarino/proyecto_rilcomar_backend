package com.proyecto.rilcomar.mappers;

import com.proyecto.rilcomar.dtos.PalletDto;
import com.proyecto.rilcomar.dtos.PalletSimpleDto;
import com.proyecto.rilcomar.entities.Pallet;
import com.proyecto.rilcomar.enums.EstadoPalletEnum;
import com.proyecto.rilcomar.enums.MaterialEnum;

import java.util.ArrayList;
import java.util.Optional;

public class PalletMapper {

    public static PalletDto buildDto(Pallet pallet) {
        return PalletDto.builder()
                .id(pallet.getId())
                .estado(pallet.getEstado().name())
                .tipo(pallet.getTipo().name())
                .formato(pallet.getFormato())
                .peso(pallet.getPeso())
                .observaciones(pallet.getObservaciones())
                .ubicacion(pallet.getUbicacion())
                .historial(Optional.of(pallet.getHistorial()
                        .stream()
                        .map(pedidoPallet -> PedidoMapper.buildSimpleDto(pedidoPallet.getPedido()))
                        .toList()).orElse(new ArrayList<>())
                )
                .build();
    }

    public static PalletSimpleDto buildSimpleDto(Pallet pallet) {
        return PalletSimpleDto.builder()
                .id(pallet.getId())
                .estado(pallet.getEstado().name())
                .tipo(pallet.getTipo().name())
                .formato(pallet.getFormato())
                .peso(pallet.getPeso())
                .observaciones(pallet.getObservaciones())
                .ubicacion(pallet.getUbicacion())
                .build();
    }

    public static Pallet buildEntity(PalletDto pallet) {
        return Pallet.builder()
                .id(pallet.getId())
                .estado(EstadoPalletEnum.valueOf(pallet.getEstado()))
                .tipo(MaterialEnum.valueOf(pallet.getTipo()))
                .formato(pallet.getFormato())
                .peso(pallet.getPeso())
                .observaciones(pallet.getObservaciones())
                .ubicacion(pallet.getUbicacion())
                .build();
    }

    public static Pallet buildEntity(PalletSimpleDto pallet) {
        return Pallet.builder()
                .id(pallet.getId())
                .estado(EstadoPalletEnum.valueOf(pallet.getEstado()))
                .tipo(MaterialEnum.valueOf(pallet.getTipo()))
                .formato(pallet.getFormato())
                .peso(pallet.getPeso())
                .observaciones(pallet.getObservaciones())
                .ubicacion(pallet.getUbicacion())
                .build();
    }
}
