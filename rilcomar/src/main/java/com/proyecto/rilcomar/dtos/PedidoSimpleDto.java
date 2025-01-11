package com.proyecto.rilcomar.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoSimpleDto {
    private int id;
    private String estado;
    private ClienteDto cliente;
    private String fechaCreacion;
    private String fechaEntrega;
    private String ultimaActualizacion;
    private String ubicacion;
}
