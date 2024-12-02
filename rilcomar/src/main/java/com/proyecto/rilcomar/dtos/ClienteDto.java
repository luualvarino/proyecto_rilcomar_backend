package com.proyecto.rilcomar.dtos;

import com.proyecto.rilcomar.entities.Cliente;
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

    public static ClienteDto build (Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .mail(cliente.getMail())
                .build();
    }
}
