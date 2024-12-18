package com.proyecto.rilcomar.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private int id;
    private String nombre;
    private String telefono;
    private String mail;
}
