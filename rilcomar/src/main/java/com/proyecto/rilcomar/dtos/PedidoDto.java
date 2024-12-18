package com.proyecto.rilcomar.dtos;

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
}
