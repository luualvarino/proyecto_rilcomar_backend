package com.proyecto.rilcomar.dtos;

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
    private String formato;
    private double peso;
    private String observaciones;
    private String ubicacion;
    private List<PedidoDto> historial;
}
