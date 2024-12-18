package com.proyecto.rilcomar.dtos;

import lombok.*;

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
    private String fechaCreacion;
    private String fechaEntrega;
    private String ultimaActualizacion;
    private String ubicacion;
    private List<PalletDto> pallets;
}
