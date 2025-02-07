package com.proyecto.rilcomar.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PalletSimpleDto {
    private int id;
    private String estado;
    private String tipo;
    private String formato;
    private double peso;
    private String observaciones;
    private String ubicacion;
    private String qrCodeUrl;
}
