package com.proyecto.rilcomar.dtos;

import com.proyecto.rilcomar.entities.Pallet;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PalletDto {
    private int id;
    private String estado;
    private String tipo;
    private boolean estaDisponible;

    public static PalletDto build(Pallet pallet){
        return PalletDto.builder()
                .id(pallet.getId())
                .estado(pallet.getEstado())
                .tipo(pallet.getTipo())
                .estaDisponible(pallet.isEstaDisponible())
                .build();
    }
}
